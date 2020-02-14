package com.robertx22.mine_and_slash.database.stats.mods.percent.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class CriticalDamagePercent extends StatMod {

    public CriticalDamagePercent() {
    }

    @Override
    public String GUID() {
        return "critical_damage_percent";
    }

    @Override
    public float Min() {
        return 4;

    }

    @Override
    public float Max() {
        return 15;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
        return CriticalDamage.getInstance();
    }

}
