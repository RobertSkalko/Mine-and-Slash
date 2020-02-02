package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.config.forge.ModConfig;

public class CommonRunedGear extends CommonGear {

    private CommonRunedGear() {
        super();
    }

    public static CommonRunedGear getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNED_ITEMS.COMMON_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final CommonRunedGear INSTANCE = new CommonRunedGear();
    }
}
