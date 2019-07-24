package com.robertx22.mine_and_slash.database.rarities.spells;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.SpellRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseLegendary;

public class LegendarySpell extends BaseLegendary implements SpellRarity {

    @Override
    public MinMax SpellBasePercents() {
        return new MinMax(65, 90);
    }

    @Override
    public MinMax SpellScalingPercents() {
        return new MinMax(65, 90);
    }

    @Override
    public float specialItemChance() {
        return 5.5F;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.SPELLS.LEGENDARY_WEIGHT.get();
    }
}