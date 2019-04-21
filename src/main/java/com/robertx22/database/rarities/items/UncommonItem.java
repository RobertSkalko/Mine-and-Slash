package com.robertx22.database.rarities.items;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.base.BaseUncommon;
import com.robertx22.mmorpg.config.ModConfig;

public class UncommonItem extends BaseUncommon implements ItemRarity {

    @Override
    public int AffixChance() {
	return 15;
    }

    @Override
    public MinMax SecondaryStatsAmount() {
	return new MinMax(1, 1);
    }

    @Override
    public MinMax StatPercents() {
	return new MinMax(5, 65);
    }

    @Override
    public int SetChance() {
	return 25;
    }

    @Override
    public float specialItemChance() {
	return 1.5F;
    }

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.ITEMS.UNCOMMON_WEIGHT;
    }

    @Override
    public int runeSlots() {
	return 2;
    }

    @Override
    public MinMax SpawnDurabilityHit() {
	return new MinMax(55, 65);
    }

}
