package com.robertx22.mine_and_slash.database.rarities.mobs;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseRare;

public class RareMob extends BaseRare implements MobRarity {

    @Override
    public float StatMultiplier() {
        return 1.4F;
    }

    @Override
    public float DamageMultiplier() {
        return 1.4F;
    }

    @Override
    public float HealthMultiplier() {
        return 1.5F;
    }

    @Override
    public float LootMultiplier() {
        return 1.75F;
    }

    @Override
    public int MaxMobEffects() {
        return 2;
    }

    @Override
    public float ExpOnKill() {
        return 10;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MOBS.RARE_WEIGHT.get();
    }
}
