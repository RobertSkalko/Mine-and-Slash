package com.robertx22.database.rarities.runed_items;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.items.MythicalItem;

public class MythicalRunedItem extends MythicalItem {

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.RUNED_ITEMS.MYTHICAL_WEIGHT;
    }
}
