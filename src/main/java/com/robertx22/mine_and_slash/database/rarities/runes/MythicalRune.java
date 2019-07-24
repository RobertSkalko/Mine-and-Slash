package com.robertx22.mine_and_slash.database.rarities.runes;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.database.rarities.items.MythicalItem;

public class MythicalRune extends MythicalItem implements RuneRarity {

    @Override
    public int minimumRunewordPower() {
        return 3;
    }

    @Override
    public int maximumRunewordPower() {
        return 5;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNES.MYTHICAL_WEIGHT.get();
    }
}
