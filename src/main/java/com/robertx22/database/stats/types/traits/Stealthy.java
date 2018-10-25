package com.robertx22.database.stats.types.traits;

import com.robertx22.database.stats.types.defense.Dodge;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class Stealthy extends Trait implements IAffectsOtherStats {

	public static String GUID = "Stealthy";

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public void TryAffectOtherStats(Unit unit) {

		unit.Stats.get(Dodge.GUID).Multi += 10;

	}

}
