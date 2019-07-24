package com.robertx22.mine_and_slash.database.rarities.items;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseRare;

public class RareItem extends BaseRare implements ItemRarity {

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
    public MinMax SpawnDurabilityHit() {
        return new MinMax(60, 70);
    }

    @Override
    public float itemTierPower() {
        return 1.5F;
    }

    @Override
    public float powerMultiplier() {
        return 1.4F;
    }
}


