package com.robertx22.database.stat_mods.traits.conditionals.low_dodge;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.low_dodge.LowDodgeAddCritHit;
import com.robertx22.stats.Stat;

public class LowDodgeAddCritHitFlat extends BaseTraitMod {

    @Override
    public String GUID() {
	return "LowDodgeAddCritHit";
    }

    @Override
    public Stat GetBaseStat() {
	return new LowDodgeAddCritHit();
    }

}
