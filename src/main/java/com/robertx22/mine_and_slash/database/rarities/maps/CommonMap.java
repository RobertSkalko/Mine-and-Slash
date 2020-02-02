package com.robertx22.mine_and_slash.database.rarities.maps;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseCommon;

public class CommonMap extends BaseCommon implements MapRarity {

    private CommonMap() {
    }

    public static CommonMap getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public MinMax AffixAmount() {
        return new MinMax(1, 2);
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(15, 30);
    }

    @Override
    public float specialItemChance() {
        return 5F;
    }

    @Override
    public float groupPlayChance() {
        return 0;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MAPS.COMMON_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final CommonMap INSTANCE = new CommonMap();
    }
}
