package com.robertx22.database.rarities.runes;

import com.robertx22.database.rarities.RuneRarity;
import com.robertx22.database.rarities.items.EpicItem;
import com.robertx22.mmorpg.config.ModConfig;

public class EpicRune extends EpicItem implements RuneRarity {

    @Override
    public int minimumRunewordPower() {
	return 1;
    }

    @Override
    public int maximumRunewordPower() {
	return 2;
    }

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.RUNES.EPIC_WEIGHT;
    }
}
