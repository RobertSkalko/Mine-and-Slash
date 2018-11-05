package com.robertx22.database.stats.types.traits.atronachs;

import com.robertx22.database.stats.types.elementals.damage.WaterDamage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class FrostAtronach extends Trait implements IAffectsOtherStats {

	public static String GUID = "Frost Atronach";

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public void TryAffectOtherStats(Unit unit) {

		unit.Stats.get(WaterDamage.GUID).Multi += 15;

	}

	@Override
	public String Description() {
		return "Water Damage +15% multi";
	}
}
