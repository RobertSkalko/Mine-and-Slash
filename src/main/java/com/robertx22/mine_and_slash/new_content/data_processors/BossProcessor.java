package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.new_content.data_processors.bases.ChunkProcessData;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.MobSpawner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class BossProcessor extends DataProcessor {

    public BossProcessor() {
        super("boss_mob");
    }

    @Override
    public void processImplementation(String key, BlockPos pos, IWorld world, ChunkProcessData data) {

        MobSpawner spawner = new MobSpawner(EntityRegister.randomBoss(), world.getWorld(), pos);
        spawner.spawn();

    }
}
