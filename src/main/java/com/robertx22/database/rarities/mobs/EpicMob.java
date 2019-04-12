package com.robertx22.database.rarities.mobs;

import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.rarities.base.BaseEpic;
import com.robertx22.mmorpg.config.ModConfig;

public class EpicMob extends BaseEpic implements MobRarity {

    @Override
    public float StatMultiplier() {
	return 2F;
    }

    @Override
    public float DamageMultiplier() {
	return 1.75F;
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
	return ModConfig.RarityWeightConfig.MOBS.EPIC_WEIGHT;
    }
}
