package com.robertx22.advanced_blocks.gear_factory_station;

import com.robertx22.advanced_blocks.BaseInventoryBlock;
import com.robertx22.database.lists.CreativeTabList;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGearFactory extends BaseInventoryBlock {
	public BlockGearFactory() {
		super(Material.ROCK);
		this.setCreativeTab(CreativeTabList.MyModTab);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileGearFactory();
	}

}