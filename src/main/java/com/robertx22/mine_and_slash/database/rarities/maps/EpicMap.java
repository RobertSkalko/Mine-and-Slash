package com.robertx22.mine_and_slash.database.rarities.maps;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseEpic;

public class EpicMap extends BaseEpic implements MapRarity {

    @Override
    public MinMax AffixAmount() {
        return new MinMax(2, 3);
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(50, 80);
    }

    @Override
    public float groupPlayChance() {
        return 25;
    }

    @Override
    public float specialItemChance() {
        return 15.5F;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MAPS.EPIC_WEIGHT.get();
    }
}