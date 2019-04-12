package com.robertx22.database.rarities.spells;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.SpellRarity;
import com.robertx22.database.rarities.base.BaseEpic;
import com.robertx22.mmorpg.config.ModConfig;

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
	return ModConfig.RarityWeightConfig.SPELLS.EPIC_WEIGHT;
    }
}
