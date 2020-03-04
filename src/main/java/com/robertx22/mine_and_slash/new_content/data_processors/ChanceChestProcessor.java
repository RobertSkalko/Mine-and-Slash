package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class ChanceChestProcessor extends DataProcessor {

    public ChanceChestProcessor() {
        super("chance_chest");
    }

    @Override
    public void processImplementation(BlockPos pos, IWorld world, ChunkProcessData data) {
        if (!data.chanceChest && RandomUtils.roll(80)) {
            data.chanceChest = true;
            new ChestProcessor().processImplementation(pos, world, data);
        }
    }
}
