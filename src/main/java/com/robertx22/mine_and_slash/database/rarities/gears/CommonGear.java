package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseCommon;

public class CommonGear extends BaseCommon implements GearRarity {
    @Override
    public float requirementMulti() {
        return 0.3F;
    }

    @Override
    public int AffixChance() {
        return 10;
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(3, 60);
    }

    @Override
    public int SetChance() {
        return 15;
    }

    @Override
    public float specialItemChance() {
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
    public float itemTierPower() {
        return 1;
    }

    @Override
    public float powerMultiplier() {
        return 1F;
    }

}
