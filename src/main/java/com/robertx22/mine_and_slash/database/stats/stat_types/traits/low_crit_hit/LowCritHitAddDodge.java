package com.robertx22.mine_and_slash.database.stats.stat_types.traits.low_crit_hit;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.bases.BaseTraitLowCritHit;

import java.util.Arrays;
import java.util.List;

public class LowCritHitAddDodge extends BaseTraitLowCritHit {

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new DodgeRatingFlat());

    }

    @Override
    public String GUID() {
        return "LowCritHitAddDodgeFlat";
    }

    @Override
    public String locNameForLangFile() {
        return "Dodge on Low Crit";
    }
}
