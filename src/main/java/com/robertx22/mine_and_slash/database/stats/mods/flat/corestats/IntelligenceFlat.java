package com.robertx22.mine_and_slash.database.stats.mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Intelligence;

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
        return "intelligence_flat";
    }
}
