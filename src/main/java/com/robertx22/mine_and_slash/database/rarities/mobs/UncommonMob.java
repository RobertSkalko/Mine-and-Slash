package com.robertx22.mine_and_slash.database.rarities.mobs;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseUncommon;

public class UncommonMob extends BaseUncommon implements MobRarity {

    @Override
    public float StatMultiplier() {
        return 1.2F;
    }

    @Override
    public float DamageMultiplier() {
        return 1.2F;
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

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MOBS.UNCOMMON_WEIGHT.get();
    }

}
