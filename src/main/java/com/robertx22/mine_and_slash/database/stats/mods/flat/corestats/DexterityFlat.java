package com.robertx22.mine_and_slash.database.stats.mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Dexterity;

public class DexterityFlat extends BaseCoreStatFlat {

    public DexterityFlat() {

    }

    @Override
    public Stat GetBaseStat() {
        return Dexterity.INSTANCE;
    }

    @Override
    public String GUID() {
        return "dexterity_flat";
    }

}
