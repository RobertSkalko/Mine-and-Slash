package com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.minus.all_ele_dmg;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public abstract class BaseAllEleDmgMap extends StatMod {

    public BaseAllEleDmgMap() {
    }

    @Override
    public float Min() {
        return -30;
    }

    @Override
    public float Max() {
        return -75;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

}