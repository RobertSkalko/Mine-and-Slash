package com.robertx22.database.stat_types.traits.bases;

import com.robertx22.database.stat_types.offense.CriticalHit;
import com.robertx22.saveclasses.Unit;

public abstract class BaseTraitLowCritHit extends TraitWithRequirement {

    @Override
    public boolean requirement(Unit unit) {

	return unit.MyStats.get(CriticalHit.GUID).BaseFlat < 10;

    }

    @Override
    public String descPrefix() {
	return "If Crit Hit < 10%";
    }

}
