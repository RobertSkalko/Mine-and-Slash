package com.robertx22.mine_and_slash.database.stats.types.traits.low_crit_hit;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.types.traits.bases.BaseTraitLowCritHit;

import java.util.Arrays;
import java.util.List;

public class LowCritHitAddDodge extends BaseTraitLowCritHit {

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new DodgeRatingFlat());

    }

    @Override
    public String GUID() {
        return "low_crit_hit_add_dodge_flat";
    }

    @Override
    public String locNameForLangFile() {
        return "Dodge on Low Crit";
    }
}
