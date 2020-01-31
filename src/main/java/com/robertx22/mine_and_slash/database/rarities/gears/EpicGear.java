package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseEpic;

public class EpicGear extends BaseEpic implements GearRarity {
    public EpicGear() {
    }

    public static EpicGear getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public float requirementMulti() {
        return 0.6F;
    }

    @Override
    public int AffixChance() {
        return 60;
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(20, 75);
    }

    @Override
    public int SetChance() {
        return 50;
    }

    @Override
    public float specialItemChance() {
        return 4.5F;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.ITEMS.EPIC_WEIGHT.get();
    }

    @Override
    public int runeSlots() {
        return 3;
    }

    @Override
    public float itemTierPower() {
        return 2;
    }

    @Override
    public float powerMultiplier() {
        return 1.6F;
    }

    private static class SingletonHolder {
        private static final EpicGear INSTANCE = new EpicGear();
    }
}
