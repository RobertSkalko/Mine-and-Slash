package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.config.ModConfig;

public class MythicalRunedGear extends MythicalGear {

    private MythicalRunedGear() {
        super();
    }

    public static MythicalRunedGear getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNED_ITEMS.MYTHICAL_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final MythicalRunedGear INSTANCE = new MythicalRunedGear();
    }
}
