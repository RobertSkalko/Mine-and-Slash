package com.robertx22.mine_and_slash.blocks.item_modify_station;

import com.robertx22.mine_and_slash.blocks.bases.BaseInventoryBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockGearModify extends BaseInventoryBlock {

    public BlockGearModify() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(5F));
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return new TileGearModify();

    }

}