package com.robertx22.database.stat_types.traits.atronachs;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
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

		unit.MyStats.get(SpellWaterDamage.GUID).Multi += 15;

	}

	@Override
	public String Description() {
		return "Water Damage +15% multi";
	}
}
