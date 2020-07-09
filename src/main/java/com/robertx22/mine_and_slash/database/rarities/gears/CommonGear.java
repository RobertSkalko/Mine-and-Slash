package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseCommon;

public class CommonGear extends BaseCommon implements GearRarity {
    CommonGear() {
    }

    public static CommonGear getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public float unidentifiedChance() {
        return 0;
    }

    @Override
    public MinMax affixStatPercents() {
        return StatPercents();
    }

    @Override
    public MinMax uniqueStatPercents() {
        return StatPercents();
    }

    @Override
    public int AffixChance() {
        return 0;
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(3, 25);
    }

    @Override
    public float salvageLotteryWinChance() {
        return 0.5F;
    }

    @Override
    public int Weight() {
        return 5000;
    }

    @Override
    public int maxAffixes() {
        return 0;
    }

    @Override
    public float itemTierPower() {
        return 1;
    }

    private static class SingletonHolder {
        private static final CommonGear INSTANCE = new CommonGear();
    }
}
