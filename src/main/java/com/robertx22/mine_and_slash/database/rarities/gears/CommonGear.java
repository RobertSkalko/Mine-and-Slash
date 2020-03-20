package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
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
    public float requirementMulti() {
        return 0.2F;
    }

    @Override
    public float unidentifiedChance() {
        return 0;
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
    public int SetChance() {
        return 15;
    }

    @Override
    public float salvageLotteryWinChance() {
        return 0.5F;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.ITEMS.COMMON_WEIGHT.get();
    }

    @Override
    public int runeSlots() {
        return 1;
    }

    @Override
    public MinMax secondaryStatAmount() {
        return new MinMax(0, 0);
    }

    @Override
    public float itemTierPower() {
        return 1;
    }

    private static class SingletonHolder {
        private static final CommonGear INSTANCE = new CommonGear();
    }
}
