package com.robertx22.database.rarities.spells;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.SpellRarity;
import com.robertx22.database.rarities.base.BaseLegendary;
import com.robertx22.mmorpg.config.ModConfig;

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
	return ModConfig.RarityWeightConfig.SPELLS.LEGENDARY_WEIGHT;
    }
}