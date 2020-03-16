package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.database.map_events.base.MapEvent;
import com.robertx22.mine_and_slash.new_content.data_processors.bases.ChunkProcessData;
import com.robertx22.mine_and_slash.new_content.data_processors.bases.SpawnedMob;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class MobHordeProcessor extends DataProcessor {

    public MobHordeProcessor() {
        super("mob_horde");
    }

    @Override
    public void processImplementation(String key, BlockPos pos, IWorld world, ChunkProcessData data) {

        EntityType<? extends MobEntity> type = SpawnedMob.random(data.getRoom()).type;
        MapEvent.summonMinions(type, 5, world, pos);

    }
}
