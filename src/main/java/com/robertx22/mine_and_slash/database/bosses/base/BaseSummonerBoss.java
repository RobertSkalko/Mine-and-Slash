package com.robertx22.mine_and_slash.database.bosses.base;

import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;

public abstract class BaseSummonerBoss extends com.robertx22.mine_and_slash.database.bosses.base.Boss {

    @Override
    public void onHealthTreshholdTriggered(LivingEntity en, BossData.HealthTreshhold t) {

        int amount = 2;
        if (t == BossData.HealthTreshhold.T_75) {
            amount = 4;
        } else if (t == BossData.HealthTreshhold.T_50) {
            amount = 5;
        } else if (t == BossData.HealthTreshhold.T_25) {
            amount = 10;
        } else if (t == BossData.HealthTreshhold.T_10) {
            amount = 15;
        }

        en.addPotionEffect(new EffectInstance(Effects.LEVITATION, 200, 1));
        en.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 5000, 10));

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
