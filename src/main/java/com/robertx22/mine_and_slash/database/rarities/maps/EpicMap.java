package com.robertx22.mine_and_slash.database.rarities.maps;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseEpic;

public class EpicMap extends BaseEpic implements MapRarity {

    @Override
    public MinMax AffixAmount() {
        return new MinMax(3, 4);
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(30, 55);
    }

    @Override
    public float groupPlayChance() {
        return 20;
    }

    @Override
    public float specialItemChance() {
        return 5.5F;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MAPS.EPIC_WEIGHT.get();
    }
}