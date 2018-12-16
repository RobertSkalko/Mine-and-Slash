package com.robertx22.database.rarities.mobs;

import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.rarities.base.BaseMythical;
import com.robertx22.mmorpg.ModConfig;

public class MythicalMob extends BaseMythical implements MobRarity {

    @Override
    public float StatMultiplier() {
	return 7F;
    }

    @Override
    public float DamageMultiplier() {
	return 4;
    }

    @Override
    public float HealthMultiplier() {
	return 13;
    }

    @Override
    public float LootMultiplier() {
	return 8F;
    }

    @Override
    public int MaxMobEffects() {
	return 3;
    }

    @Override
    public float ExpOnKill() {
	return 50;
    }

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.MOBS.MYTHICAL_WEIGHT;
    }

}
