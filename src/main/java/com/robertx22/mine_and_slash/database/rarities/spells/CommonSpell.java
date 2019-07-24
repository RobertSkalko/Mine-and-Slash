package com.robertx22.mine_and_slash.database.rarities.spells;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.SpellRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseCommon;

public class CommonSpell extends BaseCommon implements SpellRarity {

    @Override
    public MinMax SpellBasePercents() {
        return new MinMax(25, 50);
    }

    @Override
    public MinMax SpellScalingPercents() {
        return new MinMax(25, 50);
    }

    @Override
    public float specialItemChance() {
        return 0.5F;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.SPELLS.COMMON_WEIGHT.get();
    }
}
