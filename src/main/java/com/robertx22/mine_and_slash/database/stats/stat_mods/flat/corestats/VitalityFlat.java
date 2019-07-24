package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.core_stats.Vitality;

public class VitalityFlat extends BaseCoreStatFlat {

    public VitalityFlat() {
        super();
    }

    @Override
    public Stat GetBaseStat() {
        return new Vitality();
    }

    @Override
    public String GUID() {
        return "VitalityFlat";
    }
}
