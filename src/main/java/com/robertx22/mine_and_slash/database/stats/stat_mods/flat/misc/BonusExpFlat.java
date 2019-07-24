package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.misc.BonusExp;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class BonusExpFlat extends StatMod {
    @Override
    public Stat GetBaseStat() {
        return new BonusExp();
    }

    @Override
    public float Min() {
        return 10;
    }

    @Override
    public float Max() {
        return 20;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return "bonusexpflat";

    }
}
