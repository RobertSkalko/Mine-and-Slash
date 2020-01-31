package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseUnique;

public class UniqueGear extends BaseUnique implements GearRarity {

    private UniqueGear() {
    }

    public static UniqueGear getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public float requirementMulti() {
        return 1F;
    }

    @Override
    public int Weight() {
        return 0;
    }

    @Override
    public int AffixChance() {
        return 25;
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(25, 100);
    }

    @Override
    public int SetChance() {
        return 100;
    }

    @Override
    public float specialItemChance() {
        return 100;
    }

    @Override
    public int runeSlots() {
        return 0;
    }

    @Override
    public float itemTierPower() {
        return 3.5F;
    }

    @Override
    public float powerMultiplier() {
        return 15F;
    }

    private static class SingletonHolder {
        private static final UniqueGear INSTANCE = new UniqueGear();
    }
}