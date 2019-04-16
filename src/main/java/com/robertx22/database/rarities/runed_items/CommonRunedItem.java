package com.robertx22.database.rarities.runed_items;

import com.robertx22.database.rarities.items.CommonItem;
import com.robertx22.mmorpg.config.ModConfig;

public class CommonRunedItem extends CommonItem {

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.RUNED_ITEMS.COMMON_WEIGHT;
    }
}
