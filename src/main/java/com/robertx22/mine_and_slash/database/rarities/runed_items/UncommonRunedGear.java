package com.robertx22.mine_and_slash.database.rarities.runed_items;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.gears.UncommonGear;

public class UncommonRunedGear extends UncommonGear {

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNED_ITEMS.UNCOMMON_WEIGHT.get();

    }
}
