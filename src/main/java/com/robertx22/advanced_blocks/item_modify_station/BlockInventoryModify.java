package com.robertx22.advanced_blocks.item_modify_station;

import com.robertx22.advanced_blocks.BaseInventoryBlock;
import com.robertx22.customitems.oldreplacesoon.NewBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInventoryModify extends BaseInventoryBlock {
	public BlockInventoryModify() {
		super(Material.ROCK);
		this.setCreativeTab(NewBlocks.MyModTab);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileInventoryModify();
	}

}