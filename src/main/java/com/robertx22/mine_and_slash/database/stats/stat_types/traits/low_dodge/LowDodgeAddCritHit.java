package com.robertx22.mine_and_slash.database.stats.stat_types.traits.low_dodge;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.bases.BaseTraitLowDodge;

import java.util.Arrays;
import java.util.List;

public class LowDodgeAddCritHit extends BaseTraitLowDodge {

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new CriticalHitFlat());

    }

    @Override
    public String GUID() {
        return "LowDodgeAddCritHit";
    }

    @Override
    public String locNameForLangFile() {
        return "Crit Hit on Low Dodge";
    }
}
