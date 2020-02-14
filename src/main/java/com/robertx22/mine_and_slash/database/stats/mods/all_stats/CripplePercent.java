package com.robertx22.mine_and_slash.database.stats.mods.all_stats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class CripplePercent extends BaseAllStatsMod {

    public CripplePercent(Stat stat) {
        super(stat);

    }

    @Override
    public BaseAllStatsMod newGeneratedInstance(Stat stat) {
        return new CripplePercent(stat);
    }

    @Override
    public float Min() {
        return -25;
    }

    @Override
    public float Max() {
        return -50;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Percent;
    }

    @Override
    public String GUID() {
        return "cripple_" + stat.GUID() + "_percent";
    }

}
