package com.robertx22.mine_and_slash.database.stats.mods.percent.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class MajorCriticalDamagePercent extends StatMod {

    public MajorCriticalDamagePercent() {
    }

    @Override
    public String GUID() {
        return "major_critical_damage_percent";
    }

    @Override
    public float Min() {
        return 15;

    }

    @Override
    public float Max() {
        return 30;
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
