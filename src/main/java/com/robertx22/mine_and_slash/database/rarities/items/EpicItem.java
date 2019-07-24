package com.robertx22.mine_and_slash.database.rarities.items;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseEpic;

public class EpicItem extends BaseEpic implements ItemRarity {
    public EpicItem() {
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
    public MinMax SpawnDurabilityHit() {
        return new MinMax(70, 80);
    }

    @Override
    public float itemTierPower() {
        return 2;
    }

    @Override
    public float powerMultiplier() {
        return 1.6F;
    }
}
