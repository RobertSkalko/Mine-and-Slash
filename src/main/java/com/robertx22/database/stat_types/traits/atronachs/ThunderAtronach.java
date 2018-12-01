package com.robertx22.database.stat_types.traits.atronachs;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class ThunderAtronach extends Trait implements IAffectsOtherStats {

    public static String GUID = "Thunder Atronach";

    @Override
    public String Name() {
	return GUID;
    }

    @Override
    public void TryAffectOtherStats(Unit unit) {

	unit.MyStats.get(SpellThunderDamage.GUID).Multi += 15;

    }

    @Override
    public String Description() {
	return "Spell Thunder Damage +15% multi";
    }
}
