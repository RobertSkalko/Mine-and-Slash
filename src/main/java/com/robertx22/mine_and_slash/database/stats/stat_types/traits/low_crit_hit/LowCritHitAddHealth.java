package com.robertx22.mine_and_slash.database.stats.stat_types.traits.low_crit_hit;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.multi.defense.HealthMulti;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.bases.BaseTraitLowCritHit;

import java.util.Arrays;
import java.util.List;

public class LowCritHitAddHealth extends BaseTraitLowCritHit {

    @Override
    public String GUID() {
        return "LowCritHitAddHealthMulti";
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new HealthMulti());

    }

    @Override
    public String locNameForLangFile() {
        return "Health on Low Crit";
    }
}
