package com.robertx22.database.stat_mods.traits.conditionals.low_crit_hit;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.low_crit_hit.LowCritHitAddHealth;
import com.robertx22.stats.Stat;

public class LowCritAddHealthFlat extends BaseTraitMod {

    @Override
    public String GUID() {
	return "LowCritAddHealthFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new LowCritHitAddHealth();
    }

}
