package com.robertx22.dimensions.blocks;

import java.util.Random;

import com.robertx22.database.lists.CreativeTabList;
import com.robertx22.dimensions.MyTeleporter;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.MapWorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class MyPortalBlock extends BlockPortal implements ITileEntityProvider {

	public MyPortalBlock() {

		this.setRegistryName(new ResourceLocation(Ref.MODID, "portal"));
		this.setCreativeTab(CreativeTabList.MyModTab);

	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new MyPortalBlock());

	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		return;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		return;
	}

	@Override
	public boolean trySpawnPortal(World worldIn, BlockPos pos) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return true;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		try {
			if (world.isRemote == false && entity instanceof EntityPlayer) {
				if (!entity.isRiding() && !entity.isBeingRidden() && entity.isNonBoss()) {

					TileEntity en = world.getTileEntity(pos);

					if (en instanceof TilePortalBlock) {
						TilePortalBlock portal = (TilePortalBlock) en;

						// prevents infinite teleport loop xD makes sure you dont teleport to the same
						// dimension, forever
						if (portal.id != entity.dimension) {

							DimensionManager.initDimension(portal.id);

							IWorldData data = Load.World(DimensionManager.getWorld(portal.id));

							MapWorldData worlddata = data.getWorldData();

							if (worlddata.joinedPlayerIDs.size() < 5
									|| worlddata.joinedPlayerIDs.contains(entity.getUniqueID().toString())) {

								if (worlddata.joinedPlayerIDs.contains(entity.getUniqueID().toString()) == false) {
									worlddata.joinedPlayerIDs.add(entity.getUniqueID().toString());
									data.setWorldData(worlddata);
								}

								entity.sendMessage(new TextComponentString(
										"You are traveling to a Map World of dimension Id: " + portal.id));

								entity.changeDimension(portal.id, new MyTeleporter((EntityPlayer) entity, portal.id));
							}
							entity.sendMessage(
									new TextComponentString("Maximum Player Count for this Map has been reached."));

						}

					}
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TilePortalBlock();
	}

}
