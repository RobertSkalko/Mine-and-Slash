package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseMagical;

public class MagicalGear extends BaseMagical implements GearRarity {
    MagicalGear() {
    }

    public static MagicalGear getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public float unidentifiedChance() {
        return 0;
    }

    @Override
    public float statReqMulti() {
        return 0.4F;
    }

    @Override
    public int AffixChance() {
        return 15;
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
    public MinMax StatPercents() {
        return new MinMax(20, 100);
    }

    @Override
    public float salvageLotteryWinChance() {
        return 1.5F;
    }

    @Override
    public int Weight() {
        return 2000;
    }

    @Override
    public int maxAffixes() {
        return 2;
    }

    @Override
    public int minAffixes() {
        return 1;
    }

    @Override
    public float itemTierPower() {
        return 1.2F;
    }

    private static class SingletonHolder {
        private static final MagicalGear INSTANCE = new MagicalGear();
    }
}
