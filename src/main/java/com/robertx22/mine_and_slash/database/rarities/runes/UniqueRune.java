package com.robertx22.mine_and_slash.database.rarities.runes;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.database.rarities.items.UniqueItem;

public class UniqueRune extends UniqueItem implements RuneRarity {

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNES.MYTHICAL_WEIGHT.get();
    }
}

