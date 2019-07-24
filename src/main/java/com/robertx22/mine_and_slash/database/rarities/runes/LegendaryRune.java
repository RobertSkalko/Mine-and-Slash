package com.robertx22.mine_and_slash.database.rarities.runes;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.database.rarities.items.LegendaryItem;

public class LegendaryRune extends LegendaryItem implements RuneRarity {

    @Override
    public int minimumRunewordPower() {
        return 2;
    }

    @Override
    public int maximumRunewordPower() {
        return 3;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNES.LEGENDARY_WEIGHT.get();
    }
}
