package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.core_stats.Wisdom;

public class WisdomFlat extends BaseCoreStatFlat {

    public WisdomFlat() {
        super();
    }

    @Override
    public Stat GetBaseStat() {
        return new Wisdom();
    }

    @Override
    public String GUID() {
        return "WisdomFlat";
    }
}
