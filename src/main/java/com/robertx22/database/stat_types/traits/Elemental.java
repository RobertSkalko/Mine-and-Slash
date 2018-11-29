package com.robertx22.database.stat_types.traits;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class Elemental extends Trait implements IAffectsOtherStats {

    public static String GUID = "Elemental";

    @Override
    public String Name() {
	return GUID;
    }

    @Override
    public void TryAffectOtherStats(Unit unit) {

	unit.MyStats.get(SpellFireDamage.GUID).Multi += 5;
	unit.MyStats.get(SpellWaterDamage.GUID).Multi += 5;
	unit.MyStats.get(SpellThunderDamage.GUID).Multi += 5;
	unit.MyStats.get(SpellNatureDamage.GUID).Multi += 5;

    }

    @Override
    public String Description() {
	return "All elemental damage +5% multi";
    }
}
