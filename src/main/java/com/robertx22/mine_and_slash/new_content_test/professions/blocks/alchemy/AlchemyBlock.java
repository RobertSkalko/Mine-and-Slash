package com.robertx22.mine_and_slash.new_content_test.professions.blocks.alchemy;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionBlock;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class AlchemyBlock extends ProfessionBlock {

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new AlchemyTile();
    }

}
