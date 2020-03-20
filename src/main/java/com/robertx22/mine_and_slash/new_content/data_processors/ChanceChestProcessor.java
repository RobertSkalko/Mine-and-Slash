package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.new_content.data_processors.bases.ChunkProcessData;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class ChanceChestProcessor extends DataProcessor {

    public ChanceChestProcessor() {
        super("chance_chest", Type.CONTAINS);
    }

    @Override
    public void processImplementation(String key, BlockPos pos, IWorld world, ChunkProcessData data) {
        if (!data.chanceChest && RandomUtils.roll(25)) {
            data.chanceChest = true;
            new ChestProcessor().processImplementation(key, pos, world, data);
        } else {
            world.setBlockState(pos, Blocks.AIR
                .getDefaultState(), 2);

        }
    }
}
