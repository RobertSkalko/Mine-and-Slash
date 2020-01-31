package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.config.ModConfig;

public class EpicRunedGear extends EpicGear {

    private EpicRunedGear() {
    }

    public static EpicRunedGear getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNED_ITEMS.EPIC_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final EpicRunedGear INSTANCE = new EpicRunedGear();
    }
}
