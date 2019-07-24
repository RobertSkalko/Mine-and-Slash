package com.robertx22.mine_and_slash.database.rarities.mobs;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseEpic;

public class EpicMob extends BaseEpic implements MobRarity {

    @Override
    public float StatMultiplier() {
        return 2F;
    }

    @Override
    public float DamageMultiplier() {
        return 1.8F;
    }

    @Override
    public float HealthMultiplier() {
        return 2.2F;
    }

    @Override
    public float LootMultiplier() {
        return 2.2F;
    }

    @Override
    public int MaxMobEffects() {
        return 1;
    }

    @Override
    public float ExpOnKill() {
        return 15;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MOBS.EPIC_WEIGHT.get();
    }
}
