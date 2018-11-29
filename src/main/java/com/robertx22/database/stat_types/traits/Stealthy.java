package com.robertx22.database.stat_types.traits;

import com.robertx22.database.stat_types.defense.Dodge;
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

	unit.MyStats.get(Dodge.GUID).Multi += 10;

    }

    @Override
    public String Description() {
	return "+ 10% dodge multi";
    }

}
