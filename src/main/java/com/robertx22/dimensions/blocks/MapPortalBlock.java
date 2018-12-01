package com.robertx22.dimensions.blocks;

import com.robertx22.dimensions.MyTeleporter;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.MapWorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class MapPortalBlock extends BlockEndPortal {

    @GameRegistry.ObjectHolder(Ref.MODID + ":map_portal_block")
    public static Block BLOCK = null;

    public MapPortalBlock() {

	super(Material.PORTAL);
	this.setRegistryName(new ResourceLocation(Ref.MODID, "map_portal_block"));
	this.setUnlocalizedName(Ref.MODID + ":map_portal_block");
	this.setHardness(100F);

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
	event.getRegistry().register(new MapPortalBlock());

    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
	try {
	    if (world.isRemote == false && entity instanceof EntityPlayer) {
		if (!entity.isRiding() && !entity.isBeingRidden() && entity.isNonBoss()) {

		    TileEntity en = world.getTileEntity(pos);

		    if (en instanceof TileMapPortal) {
			TileMapPortal portal = (TileMapPortal) en;

			portal.ontick();

			if (portal.readyToTeleport()) {

			    // prevents infinite teleport loop xD makes sure you dont teleport to the same
			    // dimension, forever
			    if (portal.id != entity.dimension) {

				WorldServer worldserver = FMLCommonHandler.instance().getMinecraftServerInstance()
					.getWorld(portal.id); // loads the world apparently

				IWorldData data = Load.World(DimensionManager.getWorld(portal.id));

				if (data == null) {
				    entity.sendMessage(new TextComponentString(
					    "This world doesn't appear to exist, the time probably ran out"));
				    world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);

				} else if (data.isSetForDelete()) {
				    entity.sendMessage(new TextComponentString(
					    "You can't enter this world, it is closed and is set for deletition"));
				    world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);

				} else if (data.isMapWorld()) {

				    MapWorldData worlddata = data.getWorldData();

				    if (worlddata.joinedPlayerIDs.size() < 5
					    || worlddata.joinedPlayerIDs.contains(entity.getUniqueID().toString())) {

					if (worlddata.joinedPlayerIDs
						.contains(entity.getUniqueID().toString()) == false) {
					    worlddata.joinedPlayerIDs.add(entity.getUniqueID().toString());
					    data.setWorldData(worlddata);
					}

					entity.sendMessage(new TextComponentString(
						"You are traveling to a Map World of dimension Id: " + portal.id));

					World w = DimensionManager.getWorld(portal.id);

					BlockPos pos1 = w.getSpawnPoint();
					BlockPos pos2 = w.provider.getRandomizedSpawnPoint();

					entity.changeDimension(portal.id,
						new MyTeleporter(pos2, (EntityPlayer) entity, portal.id));

				    }

				    if (worlddata.joinedPlayerIDs.size() > 5) {
					entity.sendMessage(new TextComponentString(
						"Maximum Player Count for this Map has been reached."));

				    }
				} else { // if not mapworld
				    entity.sendMessage(new TextComponentString(
					    "Not a map world (was probably closed after time ran out, in case that is wrong, contact the mod author). Deleting portal block."));

				    world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);

				}
			    }

			}
		    }
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
	return new TileMapPortal();
    }

}
