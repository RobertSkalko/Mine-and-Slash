package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.database.rarities.base.BaseUncommon;
import com.robertx22.mmorpg.config.ModConfig;

public class UncommonMap extends BaseUncommon implements MapRarity {

    @Override
    public MinMax AffixAmount() {
	return new MinMax(2, 3);
    }

    @Override
    public MinMax StatPercents() {
	return new MinMax(15, 35);
    }

    @Override
    public float specialItemChance() {
	return 3.5F;
    }

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.MAPS.UNCOMMON_WEIGHT;
    }

}
