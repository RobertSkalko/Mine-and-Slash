package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.database.rarities.base.BaseCommon;
import com.robertx22.mmorpg.config.ModConfig;

public class CommonMap extends BaseCommon implements MapRarity {

    @Override
    public MinMax AffixAmount() {
	return new MinMax(1, 2);
    }

    @Override
    public MinMax StatPercents() {
	return new MinMax(10, 25);
    }

    @Override
    public float specialItemChance() {
	return 1.5F;
    }

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.MAPS.COMMON_WEIGHT;
    }
}
