package com.robertx22.mine_and_slash.database.rarities.gears;

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
        return 75;
    }

    @Override
    public float requirementMulti() {
        return 0.6F;
    }

    @Override
    public MinMax primaryStatPercents() {
        return StatPercents();
    }

    @Override
    public int AffixChance() {
        return 50;
    }

    @Override
    public MinMax secondaryStatAmount() {
        return new MinMax(1, 2);
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(30, 80);
    }

    @Override
    public int SetChance() {
        return 30;
    }

    @Override
    public float salvageLotteryWinChance() {
        return 2.5F;
    }

    @Override
    public int Weight() {
        return 2500;
    }

    @Override
    public int runeSlots() {
        return 3;
    }

    @Override
    public float itemTierPower() {
        return 1.5F;
    }

    private static class SingletonHolder {
        private static final RareGear INSTANCE = new RareGear();
    }
}


