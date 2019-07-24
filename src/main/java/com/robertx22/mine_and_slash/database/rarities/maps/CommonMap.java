package com.robertx22.mine_and_slash.database.rarities.maps;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseCommon;

public class CommonMap extends BaseCommon implements MapRarity {

    @Override
    public MinMax AffixAmount() {
        return new MinMax(1, 2);
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(10, 25);
    }

    @Override
    public float specialItemChance() {
        return 1.5F;
    }

    @Override
    public float groupPlayChance() {
        return 0;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MAPS.COMMON_WEIGHT.get();
    }
}
