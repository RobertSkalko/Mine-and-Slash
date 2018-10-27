package com.robertx22.advanced_blocks.repair_station;

import com.robertx22.customitems.oldreplacesoon.NewBlocks;
import com.robertx22.mmorpg.GuiHandler;
import com.robertx22.mmorpg.Main;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * User: brandon3055 Date: 06/01/2015
 *
 * BlockInventoryAdvanced is an advanced furnace with 5 input, 4 output and 4
 * fuel slots that smelts at twice the speed of a regular furnace. The block
 * itself doesn't do much more then any regular block except create a tile
 * entity when placed, open a gui when right clicked and drop tne inventory's
 * contents when harvested. Everything else is handled by the tile entity.
 *
 * The block model will change appearance depending on how many fuel slots are
 * burning. The amount of "block light" produced by the furnace will also
 * depending on how many fuel slots are burning.
 *
 * //Note that in 1.10.*, extending BlockContainer can cause rendering problems
 * if you don't extend getRenderType() // If you don't want to extend
 * BlockContainer, make sure to add the tile entity manually, // using
 * hasTileEntity() and createTileEntity(). See BlockContainer for a couple of
 * other important methods you may // need to implement.
 */
public class BlockInventoryFurnace extends BlockContainer {
	public BlockInventoryFurnace() {
		super(Material.ROCK);
		this.setCreativeTab(NewBlocks.MyModTab);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileInventoryFurnace();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote)
			return true;

		playerIn.openGui(Main.instance, GuiHandler.getGuiID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	// This is where you can do something when the block is broken. In this case
	// drop the inventory's contents
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
		}

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public boolean isOpaqueCube(IBlockState iBlockState) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState iBlockState) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}
}