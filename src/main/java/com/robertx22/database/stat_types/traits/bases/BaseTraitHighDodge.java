package com.robertx22.database.stat_types.traits.bases;

import com.robertx22.database.stat_types.defense.Dodge;
import com.robertx22.uncommon.capability.EntityData.UnitData;

public abstract class BaseTraitHighDodge extends ConditionalTrait {

    @Override
    public boolean condition(UnitData unit) {
	return unit.getUnit().MyStats.get(Dodge.GUID).Flat > 25;

    }

    @Override
    public String descPrefix() {
	return "If Dodge > 25%";
    }

}
