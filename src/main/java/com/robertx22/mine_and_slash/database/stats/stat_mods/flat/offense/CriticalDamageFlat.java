package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class CriticalDamageFlat extends StatMod {

    public CriticalDamageFlat() {

    }

    @Override
    public String GUID() {
        return "CriticalDamageFlat";
    }

    @Override
    public float Min() {
        return 5;

    }

    @Override
    public float Max() {
        return 15;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new CriticalDamage();
    }

}
