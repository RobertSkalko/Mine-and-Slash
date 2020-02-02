package com.robertx22.mine_and_slash.database.rarities.gears;

import com.robertx22.mine_and_slash.config.forge.ModConfig;

public class UncommonRunedGear extends UncommonGear {

    private UncommonRunedGear() {
        super();
    }

    public static UncommonRunedGear getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNED_ITEMS.UNCOMMON_WEIGHT.get();

    }

    private static class SingletonHolder {
        private static final UncommonRunedGear INSTANCE = new UncommonRunedGear();
    }
}
