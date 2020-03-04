package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.database.map_events.base.MapEvent;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class MobProcessor extends DataProcessor {

    public MobProcessor() {
        super("mob");
    }

    @Override
    public void processImplementation(BlockPos pos, IWorld world, ChunkProcessData data) {

        EntityType<? extends MobEntity> type = randomMob();

        MapEvent.summonMinion(type, world, pos);

    }
}
