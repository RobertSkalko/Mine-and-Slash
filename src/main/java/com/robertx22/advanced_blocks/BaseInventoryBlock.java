package com.robertx22.advanced_blocks;

import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.gui.GuiHandler;

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

public abstract class BaseInventoryBlock extends BlockContainer {
    protected BaseInventoryBlock(Material materialIn) {
	super(materialIn);

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
