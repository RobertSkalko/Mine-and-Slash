package com.robertx22.database.rarities.spells;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.SpellRarity;
import com.robertx22.database.rarities.base.BaseCommon;
import com.robertx22.mmorpg.config.ModConfig;

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
	return ModConfig.RarityWeightConfig.SPELLS.COMMON_WEIGHT;
    }
}
