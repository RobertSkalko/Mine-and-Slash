package com.robertx22.database.rarities.runed_items;

import com.robertx22.database.rarities.items.LegendaryItem;
import com.robertx22.mmorpg.config.ModConfig;

public class LegendaryRunedItem extends LegendaryItem {

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.RUNED_ITEMS.LEGENDARY_WEIGHT;
    }
}
