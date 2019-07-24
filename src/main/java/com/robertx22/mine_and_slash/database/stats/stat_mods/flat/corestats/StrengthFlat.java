package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.core_stats.Strength;

public class StrengthFlat extends BaseCoreStatFlat {

    public StrengthFlat() {
        super();
    }

    @Override
    public Stat GetBaseStat() {
        return new Strength();
    }

    @Override
    public String GUID() {
        return "StrengthFlat";
    }
}
