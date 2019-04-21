package com.robertx22.database.rarities.items;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.base.BaseRare;
import com.robertx22.mmorpg.config.ModConfig;

public class RareItem extends BaseRare implements ItemRarity {

    @Override
    public int AffixChance() {
	return 25;
    }

    @Override
    public MinMax SecondaryStatsAmount() {
	return new MinMax(1, 2);
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
	return ModConfig.RarityWeightConfig.ITEMS.RARE_WEIGHT;
    }

    @Override
    public int runeSlots() {
	return 2;
    }

    @Override
    public MinMax SpawnDurabilityHit() {
	return new MinMax(60, 70);
    }

}
