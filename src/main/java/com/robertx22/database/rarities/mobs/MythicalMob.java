package com.robertx22.database.rarities.mobs;

import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.rarities.base.BaseMythical;

public class MythicalMob extends BaseMythical implements MobRarity {

    @Override
    public float StatMultiplier() {
	return 10;
    }

    @Override
    public float DamageMultiplier() {
	return 5;
    }

    @Override
    public float HealthMultiplier() {
	return 14;
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

}
