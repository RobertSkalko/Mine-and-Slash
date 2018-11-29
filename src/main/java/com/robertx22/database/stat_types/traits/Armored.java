package com.robertx22.database.stat_types.traits;

import com.robertx22.database.stat_types.defense.Armor;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class Armored extends Trait implements IAffectsOtherStats {

    public static String GUID = "Armored";

    @Override
    public String Name() {
	return GUID;
    }

    @Override
    public void TryAffectOtherStats(Unit unit) {
	unit.MyStats.get(Armor.GUID).Multi += 15;
    }

    @Override
    public String Description() {
	return "Armor + 15% multi";
    }
}
