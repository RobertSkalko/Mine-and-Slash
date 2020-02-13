package com.robertx22.mine_and_slash.database.stats.types.traits.low_crit_hit;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.HealthMulti;
import com.robertx22.mine_and_slash.database.stats.types.traits.bases.BaseTraitLowCritHit;

import java.util.Arrays;
import java.util.List;

public class LowCritHitAddHealth extends BaseTraitLowCritHit {

    @Override
    public String GUID() {
        return "low_crit_hit_add_health_multi";
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
