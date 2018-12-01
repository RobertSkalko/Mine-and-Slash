package com.robertx22.database.stat_types.traits.bad_and_good;

import com.robertx22.database.stat_types.offense.PhysicalDamage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class Barbarian extends Trait implements IAffectsOtherStats {

    public static String GUID = "Barbarian";

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public void TryAffectOtherStats(Unit unit) {

	unit.manaData().Multi -= 15;
	unit.MyStats.get(PhysicalDamage.GUID).Multi += 15;

    }

    @Override
    public String Description() {
	return "Damage +15% multi, Mana -15% multi";
    }
}
