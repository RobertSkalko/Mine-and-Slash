package com.robertx22.database.stats.types.traits.bad_ones;

import com.robertx22.database.stats.types.offense.PhysicalDamage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class Crippled extends Trait implements IAffectsOtherStats {

	public static String GUID = "Crippled";

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public void TryAffectOtherStats(Unit unit) {

		unit.health().Multi -= 10;
		unit.Stats.get(PhysicalDamage.GUID).Multi -= 5;

	}

}
