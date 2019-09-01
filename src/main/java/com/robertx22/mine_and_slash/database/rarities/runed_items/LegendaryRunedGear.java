package com.robertx22.mine_and_slash.database.rarities.runed_items;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.gears.LegendaryGear;

public class LegendaryRunedGear extends LegendaryGear {

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNED_ITEMS.
                LEGENDARY_WEIGHT.get();
    }
}
