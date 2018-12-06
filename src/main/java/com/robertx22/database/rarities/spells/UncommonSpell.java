package com.robertx22.database.rarities.spells;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.SpellRarity;
import com.robertx22.database.rarities.base.BaseUncommon;

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

}
