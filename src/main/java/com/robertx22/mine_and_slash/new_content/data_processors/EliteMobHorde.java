package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.database.map_events.base.MapEvent;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class EliteMobHorde extends DataProcessor {

    public EliteMobHorde() {
        super("elite_mob_horde");
    }

    @Override
    public void processImplementation(BlockPos pos, IWorld world) {

        EntityType<? extends MobEntity> type = randomMob();

        for (int i = 0; i < 3; i++) {
            MapEvent.summonElite(type, world, pos);
        }
    }
}
