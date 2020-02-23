package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.database.map_events.base.MapEvent;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class BossProcessor extends DataProcessor {

    public BossProcessor() {
        super("boss_mob");
    }

    @Override
    public void processImplementation(BlockPos pos, IWorld world) {

        EntityType<? extends MobEntity> type = EntityType.WITHER_SKELETON;

        MapEvent.summonBoss(type, world.getWorld(), pos, SlashRegistry.Bosses()
            .random());

    }
}
