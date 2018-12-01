package com.robertx22.database.stat_types.traits.bad_ones;

import com.robertx22.database.stat_types.offense.PhysicalDamage;
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

	unit.healthData().Multi -= 10;
	unit.MyStats.get(PhysicalDamage.GUID).Multi -= 5;

    }

    @Override
    public String Description() {
	return "Health -10% multi, Physical Damage -5% multi";
    }
}
