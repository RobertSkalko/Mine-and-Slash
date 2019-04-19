package com.robertx22.database.rarities.mobs;

import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.rarities.base.BaseLegendary;
import com.robertx22.mmorpg.config.ModConfig;

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
	return 6;
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
	return ModConfig.RarityWeightConfig.MOBS.LEGENDARY_WEIGHT;
    }

}
