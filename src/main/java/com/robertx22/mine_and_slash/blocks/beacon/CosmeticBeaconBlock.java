package com.robertx22.mine_and_slash.blocks.beacon;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class CosmeticBeaconBlock extends Block {

    public CosmeticBeaconBlock() {
        super(Block.Properties.from(Blocks.AIR));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return new CosmeticBeaconTile();

    }
}
