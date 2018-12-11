package com.robertx22.database.stat_types.traits.low_crit_hit;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stat_types.traits.bases.BaseTraitLowCritHit;
import com.robertx22.stats.StatMod;

public class LowCritHitAddDodge extends BaseTraitLowCritHit {

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new DodgeFlat());

    }

    @Override
    public String Guid() {
	return "LowCritHitAddDodgeFlat";
    }

    @Override
    public String unlocString() {
	return "dodge_on_low_crit";
    }

}
