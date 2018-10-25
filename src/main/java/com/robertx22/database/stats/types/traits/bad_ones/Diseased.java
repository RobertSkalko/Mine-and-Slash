package com.robertx22.database.stats.types.traits.bad_ones;

import com.robertx22.database.stats.types.resources.HealthRegen;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class Diseased extends Trait implements IAffectsOtherStats {

	public static String GUID = "Diseased";

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public void TryAffectOtherStats(Unit unit) {

		unit.Stats.get(HealthRegen.GUID).Multi -= 20;

	}

}
