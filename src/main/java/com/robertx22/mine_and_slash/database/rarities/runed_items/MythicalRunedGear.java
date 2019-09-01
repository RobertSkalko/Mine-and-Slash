package com.robertx22.mine_and_slash.database.rarities.runed_items;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.gears.MythicalGear;

public class MythicalRunedGear extends MythicalGear {

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNED_ITEMS.MYTHICAL_WEIGHT.get();
    }
}
