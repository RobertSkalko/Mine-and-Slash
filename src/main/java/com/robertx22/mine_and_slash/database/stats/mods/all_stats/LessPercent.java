package com.robertx22.mine_and_slash.database.stats.mods.all_stats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LessPercent extends BaseAllStatsMod {

    public LessPercent(Stat stat) {
        super(stat);

    }

    @Override
    public BaseAllStatsMod newGeneratedInstance(Stat stat) {
        return new LessPercent(stat);
    }

    @Override
    public float Min() {
        return -10;
    }

    @Override
    public float Max() {
        return -20;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Percent;
    }

    @Override
    public String GUID() {
        return "less_" + stat.GUID() + "_percent";
    }

}
