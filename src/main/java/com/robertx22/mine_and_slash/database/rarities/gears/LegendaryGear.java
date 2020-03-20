package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseLegendary;

public class LegendaryGear extends BaseLegendary implements GearRarity {
    LegendaryGear() {
    }

    public static LegendaryGear getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public float requirementMulti() {
        return 1;
    }

    @Override
    public float unidentifiedChance() {
        return 99;
    }

    @Override
    public int AffixChance() {
        return 100;
    }

    @Override
    public MinMax secondaryStatAmount() {
        return new MinMax(3, 3);
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(50, 100);
    }

    @Override
    public int SetChance() {
        return 50;
    }

    @Override
    public float salvageLotteryWinChance() {
        return 15;
    }

    @Override
    public int Weight() {
        return 50;
    }

    @Override
    public int runeSlots() {
        return 5;
    }

    @Override
    public float itemTierPower() {
        return 3;
    }

    private static class SingletonHolder {
        private static final LegendaryGear INSTANCE = new LegendaryGear();
    }
}
