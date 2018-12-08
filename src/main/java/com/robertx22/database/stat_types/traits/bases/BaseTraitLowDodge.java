package com.robertx22.database.stat_types.traits.bases;

import com.robertx22.database.stat_types.defense.Dodge;
import com.robertx22.uncommon.capability.EntityData.UnitData;

public abstract class BaseTraitLowDodge extends ConditionalTrait {

    @Override
    public boolean condition(UnitData unit) {
	return unit.getUnit().MyStats.get(Dodge.GUID).Flat < 5;

    }

    @Override
    public String descPrefix() {
	return "If Dodge < 5%";
    }

}
