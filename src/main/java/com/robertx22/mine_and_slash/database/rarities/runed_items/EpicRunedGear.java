package com.robertx22.mine_and_slash.database.rarities.runed_items;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.gears.EpicGear;

public class EpicRunedGear extends EpicGear {

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNED_ITEMS.EPIC_WEIGHT.get();
    }
}
