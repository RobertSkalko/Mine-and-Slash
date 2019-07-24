package com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalHit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class MajorCriticalHitPercent extends StatMod {

    public MajorCriticalHitPercent() {
    }

    @Override
    public String GUID() {
        return "MajorCriticalHitPercent";

    }

    @Override
    public float Min() {
        return 10;

    }

    @Override
    public float Max() {
        return 25;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
        return new CriticalHit();
    }

}
