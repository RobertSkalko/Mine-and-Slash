package com.robertx22.database.rarities.runed_items;

import com.robertx22.database.rarities.items.RareItem;
import com.robertx22.mmorpg.config.ModConfig;

public class RareRunedItem extends RareItem {

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.RUNED_ITEMS.RARE_WEIGHT;
    }
}
