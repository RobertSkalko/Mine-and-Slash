package com.robertx22.database.stat_types.traits.atronachs;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class EarthAtronach extends Trait implements IAffectsOtherStats {

    public static String GUID = "Earth Atronach";

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public void TryAffectOtherStats(Unit unit) {

	unit.MyStats.get(SpellNatureDamage.GUID).Multi += 15;

    }

    @Override
    public String Description() {
	return "Spell Nature Damage +15% multi";
    }
}
