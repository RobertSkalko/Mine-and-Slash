package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.config.forge.ModConfig;

public class RareRunedGear extends RareGear {

    private RareRunedGear() {
        super();
    }

    public static RareRunedGear getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNED_ITEMS.RARE_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final RareRunedGear INSTANCE = new RareRunedGear();
    }
}
