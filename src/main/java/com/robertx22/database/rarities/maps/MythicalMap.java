package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.database.rarities.base.BaseMythical;
import com.robertx22.mmorpg.config.ModConfig;

public class MythicalMap extends BaseMythical implements MapRarity {

    @Override
    public MinMax AffixAmount() {
	return new MinMax(6, 7);
    }

    @Override
    public MinMax StatPercents() {
	return new MinMax(50, 100);
    }

    @Override
    public float specialItemChance() {
	return 20F;
    }

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.MAPS.MYTHICAL_WEIGHT;
    }

}