package com.robertx22.mine_and_slash.database.stats.types.traits.low_dodge;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.types.traits.bases.BaseTraitLowDodge;

import java.util.Arrays;
import java.util.List;

public class LowDodgeAddCritHit extends BaseTraitLowDodge {

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new CriticalHitFlat());

    }

    @Override
    public String GUID() {
        return "low_dodge_add_crit_hit";
    }

    @Override
    public String locNameForLangFile() {
        return "Crit Hit on Low Dodge";
    }
}
