package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.config.ModConfig;

public class LegendaryRunedGear extends LegendaryGear {

    private LegendaryRunedGear() {
        super();
    }

    public static LegendaryRunedGear getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNED_ITEMS.
                LEGENDARY_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final LegendaryRunedGear INSTANCE = new LegendaryRunedGear();
    }
}
