package com.robertx22.mine_and_slash.database.rarities.maps;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseMythical;

public class MythicalMap extends BaseMythical implements MapRarity {

    @Override
    public MinMax AffixAmount() {
        return new MinMax(5, 6);
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(50, 100);
    }

    @Override
    public float specialItemChance() {
        return 20F;
    }

    @Override
    public float groupPlayChance() {
        return 50;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MAPS.MYTHICAL_WEIGHT.get();
    }

}