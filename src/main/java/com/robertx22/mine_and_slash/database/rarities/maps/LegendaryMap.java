package com.robertx22.mine_and_slash.database.rarities.maps;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseLegendary;

public class LegendaryMap extends BaseLegendary implements MapRarity {

    private LegendaryMap() {
    }

    public static LegendaryMap getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public MinMax AffixAmount() {
        return new MinMax(4, 5);
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(75, 100);
    }

    @Override
    public float salvageLotteryWinChance() {
        return 50;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MAPS.LEGENDARY_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final LegendaryMap INSTANCE = new LegendaryMap();
    }
}