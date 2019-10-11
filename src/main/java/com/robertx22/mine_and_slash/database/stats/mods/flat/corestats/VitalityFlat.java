package com.robertx22.mine_and_slash.database.stats.mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Vitality;

public class VitalityFlat extends BaseCoreStatFlat {

    public VitalityFlat() {
        super();
    }

    @Override
    public Stat GetBaseStat() {
        return Vitality.INSTANCE;
    }

    @Override
    public String GUID() {
        return "VitalityFlat";
    }
}
