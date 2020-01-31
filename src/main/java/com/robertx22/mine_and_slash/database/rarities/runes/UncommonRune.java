package com.robertx22.mine_and_slash.database.rarities.runes;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseUncommon;

public class UncommonRune extends BaseUncommon implements RuneRarity {

    private UncommonRune() {
    }

    public static UncommonRune getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(5, 65);
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNES.UNCOMMON_WEIGHT.get();
    }

    @Override
    public float specialItemChance() {
        return 15;
    }

    private static class SingletonHolder {
        private static final UncommonRune INSTANCE = new UncommonRune();
    }
}
