package com.robertx22.database.rarities.runed_items;

import com.robertx22.database.rarities.items.UncommonItem;
import com.robertx22.mmorpg.config.ModConfig;

public class UncommonRunedItem extends UncommonItem {

    @Override
    public int Weight() {
	return ModConfig.RarityWeightConfig.RUNED_ITEMS.UNCOMMON_WEIGHT;
    }
}
