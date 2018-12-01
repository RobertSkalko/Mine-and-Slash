package com.robertx22.database.stat_types.traits.bad_and_good;

import com.robertx22.database.stat_types.defense.Armor;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class ClumsyScholar extends Trait implements IAffectsOtherStats {

	public static String GUID = "Clumsy Scholar";

	@Override
	public String Guid() {
		return GUID;
	}

	@Override
	public void TryAffectOtherStats(Unit unit) {

		unit.manaData().Multi += 20;
		unit.MyStats.get(Armor.GUID).Multi -= 10;

	}

	@Override
	public String Description() {
		return "Mana +20% multi, Armor -10% multi";
	}
}
