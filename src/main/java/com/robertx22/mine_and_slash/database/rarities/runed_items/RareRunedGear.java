package com.robertx22.mine_and_slash.database.rarities.runed_items;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.gears.RareGear;

public class RareRunedGear extends RareGear {

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNED_ITEMS.RARE_WEIGHT.get();
    }
}
