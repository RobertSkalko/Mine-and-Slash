package com.robertx22.mine_and_slash.new_content_test.professions.blocks;

import com.robertx22.mine_and_slash.blocks.bases.BaseInventoryBlock;
import com.robertx22.mine_and_slash.blocks.map_device.TileMapDevice;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ProfessionBlock extends BaseInventoryBlock {

    public ProfessionBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(5F));
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileMapDevice();
    }

}