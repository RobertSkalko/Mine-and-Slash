package com.robertx22.database.stat_types.traits.bases;

import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;
import com.robertx22.uncommon.capability.EntityData.UnitData;

public abstract class ConditionalTrait extends Trait implements IAffectsOtherStats {

    @Override
    public void TryAffectOtherStats(UnitData unit) {

	if (this.condition(unit.getUnit())) {

	    for (StatModData mod : getStatsMods()) {

		mod.useOnPlayer(unit);
	    }
	}

    }

    public abstract boolean condition(Unit unit);

    public abstract String descPrefix();

    @Override
    public String Description() {
	return this.descPrefix();
    }

}
