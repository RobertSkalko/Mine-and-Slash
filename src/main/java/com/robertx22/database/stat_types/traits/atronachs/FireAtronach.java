package com.robertx22.database.stat_types.traits.atronachs;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
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

	unit.MyStats.get(SpellFireDamage.GUID).Multi += 15;

    }

    @Override
    public String Description() {
	return "Fire Damage +15% multi";
    }
}
