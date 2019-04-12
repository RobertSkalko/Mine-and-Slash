package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.database.rarities.base.BaseRare;
import com.robertx22.mmorpg.config.ModConfig;

public class RareMap extends BaseRare implements MapRarity {

    @Override
    public MinMax AffixAmount() {
	return new MinMax(3, 4);
    }

    @Override
    public MinMax StatPercents() {
	return new MinMax(20, 45);
    }

    @Override
    public float specialItemChance() {
	return 3.5F;
    }

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.MAPS.RARE_WEIGHT;
    }
}
