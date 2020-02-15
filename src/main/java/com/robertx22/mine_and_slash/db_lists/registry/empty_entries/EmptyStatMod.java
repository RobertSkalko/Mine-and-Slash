package com.robertx22.mine_and_slash.db_lists.registry.empty_entries;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class EmptyStatMod extends StatMod {

    private EmptyStatMod() {
    }

    public static EmptyStatMod getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public Stat GetBaseStat() {
        return EmptyStat.getInstance();
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

    private static class SingletonHolder {
        private static final EmptyStatMod INSTANCE = new EmptyStatMod();
    }
}
