package com.robertx22.mine_and_slash.database.stats.mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Wisdom;

public class WisdomFlat extends BaseCoreStatFlat {

    public WisdomFlat() {
        super();
    }

    @Override
    public Stat GetBaseStat() {
        return Wisdom.INSTANCE;
    }

    @Override
    public String GUID() {
        return "WisdomFlat";
    }
}
