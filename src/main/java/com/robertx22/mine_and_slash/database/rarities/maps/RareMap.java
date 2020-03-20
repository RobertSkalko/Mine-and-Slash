package com.robertx22.mine_and_slash.database.rarities.maps;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseRare;

public class RareMap extends BaseRare implements MapRarity {

    private RareMap() {
    }

    public static RareMap getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public MinMax AffixAmount() {
        return new MinMax(2, 3);
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(30, 60);
    }

    @Override
    public float salvageLotteryWinChance() {
        return 25;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MAPS.RARE_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final RareMap INSTANCE = new RareMap();
    }
}
