package com.robertx22.database.stat_types.traits.bases;

import com.robertx22.database.stat_types.defense.Dodge;
import com.robertx22.saveclasses.Unit;

public abstract class BaseTraitLowDodge extends ConditionalTrait {

    @Override
    public boolean condition(Unit unit) {

	return unit.MyStats.get(Dodge.GUID).BaseFlat < 5;

    }

    @Override
    public String descPrefix() {
	return "If Dodge < 5%";
    }

}
