package com.robertx22.mine_and_slash.database.rarities.runes;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseEpic;

public class EpicRune extends BaseEpic implements RuneRarity {

    private EpicRune() {
    }

    public static EpicRune getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(20, 75);
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNES.EPIC_WEIGHT.get();
    }

    @Override
    public float specialItemChance() {
        return 25;
    }

    private static class SingletonHolder {
        private static final EpicRune INSTANCE = new EpicRune();
    }
}
