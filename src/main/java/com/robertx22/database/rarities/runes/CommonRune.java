package com.robertx22.database.rarities.runes;

import com.robertx22.database.rarities.RuneRarity;
import com.robertx22.database.rarities.items.CommonItem;
import com.robertx22.mmorpg.config.ModConfig;

public class CommonRune extends CommonItem implements RuneRarity {

    @Override
    public int minimumRunewordPower() {
	return 1;
    }

    @Override
    public int maximumRunewordPower() {
	return 1;
    }

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.RUNES.COMMON_WEIGHT;
    }
}
