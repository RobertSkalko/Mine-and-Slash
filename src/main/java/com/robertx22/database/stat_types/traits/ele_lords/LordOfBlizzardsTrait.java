package com.robertx22.database.stat_types.traits.ele_lords;

import com.robertx22.database.stat_types.elementals.all_damage.AllWaterDamage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class LordOfBlizzardsTrait extends Trait implements IAffectsOtherStats {

    @Override
    public void TryAffectOtherStats(Unit unit) {
	unit.MyStats.get(new AllWaterDamage().Name()).Flat += 20;
    }

    @Override
    public String Description() {
	return "All Water Damage +20%";
    }

    @Override
    public String Name() {
	return "Lord Of Blizzards";
    }
}
