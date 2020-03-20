package com.robertx22.mine_and_slash.database.rarities.runes;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseLegendary;

public class LegendaryRune extends BaseLegendary implements RuneRarity {

    private LegendaryRune() {
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(25, 90);
    }

    public static LegendaryRune getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNES.LEGENDARY_WEIGHT.get();
    }

    @Override
    public float salvageLotteryWinChance() {
        return 35;
    }

    private static class SingletonHolder {
        private static final LegendaryRune INSTANCE = new LegendaryRune();
    }
}
