package com.robertx22.database.stat_types.traits.low_dodge;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stat_types.traits.bases.BaseTraitLowDodge;
import com.robertx22.stats.StatMod;

public class LowDodgeAddCritHit extends BaseTraitLowDodge {

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new CriticalHitFlat());

    }

    @Override
    public String Guid() {
	return "LowDodgeAddCritHit";
    }

    @Override
    public String unlocString() {
	return "crit_on_low_dodge";
    }

}
