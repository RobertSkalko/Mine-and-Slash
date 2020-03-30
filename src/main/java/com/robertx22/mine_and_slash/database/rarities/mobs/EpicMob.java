package com.robertx22.mine_and_slash.database.rarities.mobs;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseEpic;

public class EpicMob extends BaseEpic implements MobRarity {

    private EpicMob() {
    }

    public static EpicMob getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public float StatMultiplier() {
        return 3.2F;
    }

    @Override
    public float DamageMultiplier() {
        return 2.2F;
    }

    @Override
    public float HealthMultiplier() {
        return 4;
    }

    @Override
    public float LootMultiplier() {
        return 6F;
    }

    @Override
    public float oneAffixChance() {
        return 80;
    }

    @Override
    public float bothAffixesChance() {
        return 25;
    }

    @Override
    public float ExpOnKill() {
        return 25;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MOBS.EPIC_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final EpicMob INSTANCE = new EpicMob();
    }
}
