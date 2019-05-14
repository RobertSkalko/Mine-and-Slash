package com.robertx22.database.rarities.runed_items;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.items.EpicItem;

public class EpicRunedItem extends EpicItem {

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.RUNED_ITEMS.EPIC_WEIGHT;
    }
}
