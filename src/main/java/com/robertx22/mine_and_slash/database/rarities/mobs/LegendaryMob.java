package com.robertx22.mine_and_slash.database.rarities.mobs;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseLegendary;

public class LegendaryMob extends BaseLegendary implements MobRarity {

    @Override
    public float StatMultiplier() {
        return 3;
    }

    @Override
    public float DamageMultiplier() {
        return 2F;
    }

    @Override
    public float HealthMultiplier() {
        return 4;
    }

    @Override
    public float LootMultiplier() {
        return 4F;
    }

    @Override
    public int MaxMobEffects() {
        return 2;
    }

    @Override
    public float ExpOnKill() {
        return 25;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MOBS.LEGENDARY_WEIGHT.get();
    }

}
