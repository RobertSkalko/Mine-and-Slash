package com.robertx22.mine_and_slash.database.rarities.mobs;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseCommon;

public class CommonMob extends BaseCommon implements MobRarity {

    @Override
    public float StatMultiplier() {
        return 0.7F;
    }

    @Override
    public float DamageMultiplier() {
        return 1F;
    }

    @Override
    public float HealthMultiplier() {
        return 0.55F;
    }

    @Override
    public float LootMultiplier() {
        return 0.7F;
    }

    @Override
    public int MaxMobEffects() {
        return 0;
    }

    @Override
    public float ExpOnKill() {
        return 3;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MOBS.COMMON_WEIGHT.get();
    }

}
