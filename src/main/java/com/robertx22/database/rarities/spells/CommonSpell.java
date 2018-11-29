package com.robertx22.database.rarities.spells;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.SpellRarity;
import com.robertx22.database.rarities.base.BaseCommon;

public class CommonSpell extends BaseCommon implements SpellRarity {

    @Override
    public MinMax SpellBasePercents() {
	return new MinMax(25, 50);
    }

    @Override
    public MinMax SpellScalingPercents() {
	return new MinMax(25, 50);
    }

}
