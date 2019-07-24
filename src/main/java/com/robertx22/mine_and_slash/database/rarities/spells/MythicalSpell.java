package com.robertx22.mine_and_slash.database.rarities.spells;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.SpellRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseMythical;

public class MythicalSpell extends BaseMythical implements SpellRarity {

    @Override
    public MinMax SpellBasePercents() {
        return new MinMax(75, 100);
    }

    @Override
    public MinMax SpellScalingPercents() {
        return new MinMax(75, 100);
    }

    @Override
    public float specialItemChance() {
        return 10.5F;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.SPELLS.MYTHICAL_WEIGHT.get();
    }

}
