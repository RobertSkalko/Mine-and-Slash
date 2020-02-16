package com.robertx22.mine_and_slash.database.stats.mods.flat.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class HighCriticalDamageFlat extends StatMod {

    @Override
    public float Min() {
        return 20;
    }

    @Override
    public float Max() {
        return 40;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return CriticalDamage.getInstance();
    }

}

