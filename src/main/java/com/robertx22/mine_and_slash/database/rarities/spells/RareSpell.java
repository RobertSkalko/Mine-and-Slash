package com.robertx22.mine_and_slash.database.rarities.spells;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.SpellRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseRare;

public class RareSpell extends BaseRare implements SpellRarity {

    @Override
    public MinMax SpellBasePercents() {
        return new MinMax(45, 70);
    }

    @Override
    public MinMax SpellScalingPercents() {
        return new MinMax(45, 70);
    }

    @Override
    public float specialItemChance() {
        return 2.5F;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.SPELLS.RARE_WEIGHT.get();
    }
}
