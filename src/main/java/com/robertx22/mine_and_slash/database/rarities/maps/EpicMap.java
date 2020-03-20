package com.robertx22.mine_and_slash.database.rarities.maps;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseEpic;

public class EpicMap extends BaseEpic implements MapRarity {

    private EpicMap() {
    }

    public static EpicMap getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public MinMax AffixAmount() {
        return new MinMax(2, 3);
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(65, 90);
    }

    @Override
    public float groupPlayChance() {
        return 30;
    }

    @Override
    public float salvageLotteryWinChance() {
        return 30;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MAPS.EPIC_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final EpicMap INSTANCE = new EpicMap();
    }
}