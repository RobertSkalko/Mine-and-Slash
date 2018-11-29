package com.robertx22.database.stat_types.traits;

import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class Golem extends Trait implements IAffectsOtherStats {

    public static String GUID = "Golem";

    @Override
    public String Name() {
	return GUID;
    }

    @Override
    public void TryAffectOtherStats(Unit unit) {

	unit.healthData().Multi += 10;

    }

    @Override
    public String Description() {
	return "+ 10% health multi";
    }
}
