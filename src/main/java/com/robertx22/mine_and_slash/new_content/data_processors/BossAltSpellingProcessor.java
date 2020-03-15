package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.new_content.data_processors.bases.ChunkProcessData;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class BossAltSpellingProcessor extends DataProcessor {

    public BossAltSpellingProcessor() {
        super("boss");
    }

    @Override
    public void processImplementation(String key, BlockPos pos, IWorld world, ChunkProcessData data) {

        new BossProcessor().processImplementation(key, pos, world, data);

    }
}
