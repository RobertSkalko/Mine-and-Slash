package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.database.rarities.base.BaseEpic;
import com.robertx22.mmorpg.config.ModConfig;

public class EpicMap extends BaseEpic implements MapRarity {

    @Override
    public MinMax AffixAmount() {
	return new MinMax(4, 5);
    }

    @Override
    public MinMax StatPercents() {
	return new MinMax(30, 55);
    }

    @Override
    public float specialItemChance() {
	return 5.5F;
    }

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.MAPS.EPIC_WEIGHT;
    }
}