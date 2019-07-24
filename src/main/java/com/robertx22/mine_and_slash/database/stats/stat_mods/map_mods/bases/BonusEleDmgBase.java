package com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.bases;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public abstract class BonusEleDmgBase extends StatMod {

    @Override
    public float Min() {
        return 25;
    }

    @Override
    public float Max() {
        return 75;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

}