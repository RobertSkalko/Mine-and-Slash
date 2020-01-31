package com.robertx22.mine_and_slash.database.rarities.runes;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseMythical;

public class MythicalRune extends BaseMythical implements RuneRarity {

    private MythicalRune() {
    }

    public static MythicalRune getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(35, 100);
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNES.MYTHICAL_WEIGHT.get();
    }

    @Override
    public float specialItemChance() {
        return 55;
    }

    private static class SingletonHolder {
        private static final MythicalRune INSTANCE = new MythicalRune();
    }
}
