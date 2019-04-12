package com.robertx22.database.rarities.spells;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.SpellRarity;
import com.robertx22.database.rarities.base.BaseMythical;
import com.robertx22.mmorpg.config.ModConfig;

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
	return ModConfig.RarityWeightConfig.SPELLS.MYTHICAL_WEIGHT;
    }

}
