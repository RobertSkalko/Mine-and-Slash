package com.robertx22.database.rarities.items;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.base.BaseUnique;

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
    public MinMax SecondaryStatsAmount() {
	return new MinMax(0, 0);
    }

    @Override
    public MinMax StatPercents() {
	return new MinMax(25, 100);
    }

    @Override
    public int SetChance() {
	return 0;
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

}