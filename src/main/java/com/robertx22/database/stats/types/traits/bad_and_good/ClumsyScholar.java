package com.robertx22.database.stats.types.traits.bad_and_good;

import com.robertx22.database.stats.types.defense.Armor;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class ClumsyScholar extends Trait implements IAffectsOtherStats {

	public static String GUID = "Clumsy Scholar";

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public void TryAffectOtherStats(Unit unit) {

		unit.mana().Multi += 20;

		unit.Stats.get(Armor.GUID).Multi -= 10;

	}

}
