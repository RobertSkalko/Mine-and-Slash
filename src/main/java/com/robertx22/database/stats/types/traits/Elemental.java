package com.robertx22.database.stats.types.traits;

import com.robertx22.database.stats.types.elementals.damage.FireDamage;
import com.robertx22.database.stats.types.elementals.damage.NatureDamage;
import com.robertx22.database.stats.types.elementals.damage.ThunderDamage;
import com.robertx22.database.stats.types.elementals.damage.WaterDamage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class Elemental extends Trait implements IAffectsOtherStats {

	public static String GUID = "Elemental";

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public void TryAffectOtherStats(Unit unit) {

		unit.Stats.get(FireDamage.GUID).Multi += 5;
		unit.Stats.get(WaterDamage.GUID).Multi += 5;
		unit.Stats.get(ThunderDamage.GUID).Multi += 5;
		unit.Stats.get(NatureDamage.GUID).Multi += 5;

	}

	@Override
	public String Description() {
		return "All elemental damage +5% multi";
	}
}
