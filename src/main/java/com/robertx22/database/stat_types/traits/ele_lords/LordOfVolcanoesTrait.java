package com.robertx22.database.stat_types.traits.ele_lords;

import com.robertx22.database.stat_types.elementals.all_damage.AllFireDamage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class LordOfVolcanoesTrait extends Trait implements IAffectsOtherStats {

    @Override
    public void TryAffectOtherStats(Unit unit) {
	unit.MyStats.get(new AllFireDamage().Guid()).Flat += 20;
    }

    @Override
    public String Description() {
	return "All Fire Damage +20%";
    }

    @Override
    public String Guid() {
	return "Lord Of Volcanoes";
    }

    @Override
    public String Name() {
	return "Lord of Volcanoes";
    }

}
