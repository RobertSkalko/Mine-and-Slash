package com.robertx22.database.rarities.spells;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.SpellRarity;
import com.robertx22.database.rarities.base.BaseRare;

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

}
