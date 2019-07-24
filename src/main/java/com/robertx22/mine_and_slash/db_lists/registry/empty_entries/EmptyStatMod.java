package com.robertx22.mine_and_slash.db_lists.registry.empty_entries;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class EmptyStatMod extends StatMod {

    @Override
    public Stat GetBaseStat() {
        return new EmptyStat();
    }

    @Override
    public float Min() {
        return 0;
    }

    @Override
    public float Max() {
        return 0;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Percent;
    }
}
