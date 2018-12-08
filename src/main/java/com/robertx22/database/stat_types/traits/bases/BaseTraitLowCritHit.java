package com.robertx22.database.stat_types.traits.bases;

import com.robertx22.database.stat_types.offense.CriticalHit;
import com.robertx22.uncommon.capability.EntityData.UnitData;

public abstract class BaseTraitLowCritHit extends ConditionalTrait {

    @Override
    public boolean condition(UnitData unit) {

	return unit.getUnit().MyStats.get(CriticalHit.GUID).Flat < 10;

    }

    @Override
    public String descPrefix() {
	return "If Crit Hit < 10%";
    }

}
