package com.robertx22.database.stats.types.traits.atronachs;

import com.robertx22.database.stats.types.elementals.damage.FireDamage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class FireAtronach extends Trait implements IAffectsOtherStats {

	public static String GUID = "Fire Atronach";

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public void TryAffectOtherStats(Unit unit) {

		unit.MyStats.get(FireDamage.GUID).Multi += 15;

	}

	@Override
	public String Description() {
		return "Fire Damage +15% multi";
	}
}
