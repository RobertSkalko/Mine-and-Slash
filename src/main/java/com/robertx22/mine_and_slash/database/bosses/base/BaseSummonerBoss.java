package com.robertx22.mine_and_slash.database.bosses.base;

import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;

public abstract class BaseSummonerBoss extends com.robertx22.mine_and_slash.database.bosses.base.Boss {

    @Override
    public void onTick(LivingEntity en) {

    }

    @Override
    public void onHealthTreshholdTriggered(LivingEntity en, BossData.HealthTreshhold t) {

        int amount = 1;
        if (t == BossData.HealthTreshhold.T_75) {
            amount = 2;
        } else if (t == BossData.HealthTreshhold.T_50) {
            amount = 3;
        } else if (t == BossData.HealthTreshhold.T_25) {
            amount = 4;
        } else if (t == BossData.HealthTreshhold.T_10) {
            amount = 5;
        }

        spawnMinions(en, amount);

    }

    public abstract EntityType<?> getMinionType(LivingEntity en);

    public void spawnMinions(LivingEntity en, int amount) {

        BlockPos p = en.getPosition();

        for (int i = 0; i < amount; i++) {

            MobEntity spawned = (MobEntity) getMinionType(en).create(en.world);

            spawnMinion(p, spawned, en.world);

        }
    }

    @Override
    public void applyStats(EntityCap.UnitData data) {

        super.applyStats(data);

    }

}
