package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.database.rarities.base.BaseLegendary;
import com.robertx22.mmorpg.config.ModConfig;

public class LegendaryMap extends BaseLegendary implements MapRarity {

    @Override
    public MinMax AffixAmount() {
	return new MinMax(5, 6);
    }

    @Override
    public MinMax StatPercents() {
	return new MinMax(40, 70);
    }

    @Override
    public float specialItemChance() {
	return 10.5F;
    }

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.MAPS.LEGENDARY_WEIGHT;
    }
}