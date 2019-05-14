package com.robertx22.database.rarities.runes;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.RuneRarity;
import com.robertx22.database.rarities.items.MythicalItem;

public class MythicalRune extends MythicalItem implements RuneRarity {

    @Override
    public int minimumRunewordPower() {
	return 3;
    }

    @Override
    public int maximumRunewordPower() {
	return 5;
    }

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.RUNES.MYTHICAL_WEIGHT;
    }
}
