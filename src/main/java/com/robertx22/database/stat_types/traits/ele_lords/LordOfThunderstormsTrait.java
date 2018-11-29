package com.robertx22.database.stat_types.traits.ele_lords;

import com.robertx22.database.stat_types.elementals.all_damage.AllThunderDamage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Trait;

public class LordOfThunderstormsTrait extends Trait implements IAffectsOtherStats {

    @Override
    public void TryAffectOtherStats(Unit unit) {
	unit.MyStats.get(new AllThunderDamage().Name()).Flat += 20;
    }

    @Override
    public String Description() {
	return "All Thunder Damage +20%";
    }

    @Override
    public String Name() {
	return "Lord Of Thunderstorms";
    }
}
