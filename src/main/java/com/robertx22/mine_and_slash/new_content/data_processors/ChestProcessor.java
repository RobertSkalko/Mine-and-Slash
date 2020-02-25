package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class ChestProcessor extends DataProcessor {

    public ChestProcessor() {
        super("chest");
    }

    @Override
    public void processImplementation(BlockPos pos, IWorld world) {

        world.setBlockState(pos, BlockRegister.MAP_CHEST.get()
            .getDefaultState(), 2);

    }
}
