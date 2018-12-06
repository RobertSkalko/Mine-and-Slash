package com.robertx22.stats;

import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.Elements;

public abstract class Trait extends Stat implements IAffectsOtherStats {

    @Override
    public void TryAffectOtherStats(UnitData unit) {
	if (this.condition(unit)) {
	    for (StatModData mod : getStatsMods()) {
		mod.useOnPlayer(unit);
	    }
	}

    }

    // override if it has a condition
    public boolean condition(UnitData unit) {
	return true;
    }

    public abstract String Description();

    @Override
    public boolean ScalesToLevel() {
	return false;
    }

    @Override
    public Elements Element() {
	return null;
    }

    @Override
    public boolean IsPercent() {
	return false;
    }

    @Override
    public int CalcVal(StatData data, UnitData Source) {

	if (data.Flat > 0) {
	    data.Value = 1;

	    return 1;

	} else {
	    data.Value = 0;
	    return 0;
	}

    }
}
