package com.robertx22.mine_and_slash.database.rarities.spells;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.SpellRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseEpic;

public class EpicSpell extends BaseEpic implements SpellRarity {

    @Override
    public MinMax SpellBasePercents() {
        return new MinMax(55, 80);
    }

    @Override
    public MinMax SpellScalingPercents() {
        return new MinMax(55, 80);
    }

    @Override
    public float specialItemChance() {
        return 3.5F;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.SPELLS.EPIC_WEIGHT.get();
    }
}
