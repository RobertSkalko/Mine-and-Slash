package com.robertx22.database.rarities.mobs;

import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.rarities.base.BaseUncommon;

public class UncommonMob extends BaseUncommon implements MobRarity {

    @Override
    public float StatMultiplier() {
	return 1.2F;
    }

    @Override
    public float DamageMultiplier() {
	return 1.25F;
    }

    @Override
    public float HealthMultiplier() {
	return 1.35F;
    }

    @Override
    public float LootMultiplier() {
	return 1.5F;
    }

    @Override
    public int MaxMobEffects() {
	return 1;
    }

    @Override
    public float ExpOnKill() {
	return 5;
    }

}
