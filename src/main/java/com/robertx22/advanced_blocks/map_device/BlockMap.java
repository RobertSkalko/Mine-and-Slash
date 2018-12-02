package com.robertx22.advanced_blocks.map_device;

import com.robertx22.advanced_blocks.BaseInventoryBlock;
import com.robertx22.db_lists.CreativeTabList;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMap extends BaseInventoryBlock {
    public BlockMap() {
	super(Material.ROCK);
	this.setCreativeTab(CreativeTabList.MyModTab);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
	return new TileMap();
    }

}