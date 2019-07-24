package com.robertx22.mine_and_slash.database.rarities.spells;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.SpellRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseUncommon;

public class UncommonSpell extends BaseUncommon implements SpellRarity {

    @Override
    public MinMax SpellBasePercents() {
        return new MinMax(35, 60);
    }

    @Override
    public MinMax SpellScalingPercents() {
        return new MinMax(35, 60);
    }

    @Override
    public float specialItemChance() {
        return 1.5F;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.SPELLS.UNCOMMON_WEIGHT.get();
    }

}
