package com.robertx22.mine_and_slash.registry.empty_entries;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

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
    public StatModTypes getModType() {
        return StatModTypes.Percent;
    }

    private static class SingletonHolder {
        private static final EmptyStatMod INSTANCE = new EmptyStatMod();
    }
}
