package com.robertx22.mine_and_slash.database.rarities.items;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseUnique;

public class UniqueItem extends BaseUnique implements ItemRarity {

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
    public MinMax SpawnDurabilityHit() {
        return new MinMax(70, 90);
    }

    @Override
    public float itemTierPower() {
        return 3.5F;
    }

    @Override
    public float powerMultiplier() {
        return 15F;
    }
}