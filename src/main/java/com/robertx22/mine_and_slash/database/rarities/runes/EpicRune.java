package com.robertx22.mine_and_slash.database.rarities.runes;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.database.rarities.items.EpicItem;

public class EpicRune extends EpicItem implements RuneRarity {

    @Override
    public int minimumRunewordPower() {
        return 1;
    }

    @Override
    public int maximumRunewordPower() {
        return 2;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNES.EPIC_WEIGHT.get();
    }
}
