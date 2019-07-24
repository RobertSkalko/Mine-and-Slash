package com.robertx22.mine_and_slash.database.rarities.maps;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseRare;

public class RareMap extends BaseRare implements MapRarity {

    @Override
    public MinMax AffixAmount() {
        return new MinMax(3, 4);
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(20, 45);
    }

    @Override
    public float specialItemChance() {
        return 3.5F;
    }

    @Override
    public float groupPlayChance() {
        return 10;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MAPS.RARE_WEIGHT.get();
    }
}
