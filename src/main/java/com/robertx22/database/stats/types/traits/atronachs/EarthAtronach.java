package com.robertx22.database.stats.types.traits.atronachs;

import com.robertx22.database.stats.types.elementals.damage.NatureDamage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class EarthAtronach extends Trait implements IAffectsOtherStats {

	public static String GUID = "Earth Atronach";

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public void TryAffectOtherStats(Unit unit) {

		unit.MyStats.get(NatureDamage.GUID).Multi += 15;

	}

	@Override
	public String Description() {
		return "Nature Damage +15% multi";
	}
}
