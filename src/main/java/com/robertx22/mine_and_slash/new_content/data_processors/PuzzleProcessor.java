package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.mmorpg.registers.common.ModBlocks;
import com.robertx22.mine_and_slash.new_content.data_processors.bases.ChunkProcessData;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class PuzzleProcessor extends DataProcessor {

    public PuzzleProcessor() {
        super("puzzle");
    }

    @Override
    public void processImplementation(String key, BlockPos pos, IWorld world, ChunkProcessData data) {

        Block block = ModBlocks.SCRABBLE.get();

        world.setBlockState(pos, block.getDefaultState(), 2);

    }
}

