package com.robertx22.mine_and_slash.database.rarities.maps;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseMythical;

public class MythicalMap extends BaseMythical implements MapRarity {

    private MythicalMap() {
    }

    public static MythicalMap getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public MinMax AffixAmount() {
        return new MinMax(3, 5);
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(75, 125);
    }

    @Override
    public float specialItemChance() {
        return 75F;
    }

    @Override
    public float groupPlayChance() {
        return 50;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MAPS.MYTHICAL_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final MythicalMap INSTANCE = new MythicalMap();
    }
}