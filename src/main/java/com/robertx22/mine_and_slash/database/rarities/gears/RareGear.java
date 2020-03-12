package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseRare;

public class RareGear extends BaseRare implements GearRarity {
    RareGear() {
    }

    public static RareGear getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public float unidentifiedChance() {
        return 33;
    }

    @Override
    public float requirementMulti() {
        return 0.5F;
    }

    @Override
    public int AffixChance() {
        return 25;
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(15, 70);
    }

    @Override
    public int SetChance() {
        return 30;
    }

    @Override
    public float specialItemChance() {
        return 2.5F;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.ITEMS.RARE_WEIGHT.get();
    }

    @Override
    public int runeSlots() {
        return 2;
    }

    @Override
    public float itemTierPower() {
        return 1.5F;
    }

    @Override
    public float powerMultiplier() {
        return 1.4F;
    }

    private static class SingletonHolder {
        private static final RareGear INSTANCE = new RareGear();
    }
}


