package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.database.map_events.base.MapEvent;
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
    public void processImplementation(BlockPos pos, IWorld world) {

        EntityType<? extends MobEntity> type = randomMob();
        MapEvent.summonMinions(type, 5, world, pos);

    }
}
