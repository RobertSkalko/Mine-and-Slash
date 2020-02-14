package com.robertx22.mine_and_slash.database.stats.mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Stamina;

public class StaminaFlat extends BaseCoreStatFlat {

    public StaminaFlat() {
        super();
    }

    @Override
    public Stat GetBaseStat() {
        return Stamina.INSTANCE;
    }

    @Override
    public String GUID() {
        return "stamina_flat";
    }
}
