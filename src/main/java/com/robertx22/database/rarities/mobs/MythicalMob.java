package com.robertx22.database.rarities.mobs;

import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.rarities.base.BaseMythical;
import com.robertx22.mmorpg.config.ModConfig;

public class MythicalMob extends BaseMythical implements MobRarity {

    @Override
    public float StatMultiplier() {
	return 5F;
    }

    @Override
    public float DamageMultiplier() {
	return 2.5F;
    }

    @Override
    public float HealthMultiplier() {
	return 15;
    }

    @Override
    public float LootMultiplier() {
	return 10F;
    }

    @Override
    public int MaxMobEffects() {
	return 3;
    }

    @Override
    public float ExpOnKill() {
	return 75;
    }

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.MOBS.MYTHICAL_WEIGHT;
    }

}
