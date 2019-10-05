package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.core_stats.Intelligence;

public class IntelligenceFlat extends BaseCoreStatFlat {

    public IntelligenceFlat() {
        super();
    }

    @Override
    public Stat GetBaseStat() {
        return Intelligence.INSTANCE;
    }

    @Override
    public String GUID() {
        return "IntelligenceFlat";
    }
}
