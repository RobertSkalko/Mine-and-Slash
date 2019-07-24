package com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.minus;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.Health;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LessHealthMap extends StatMod {

    public LessHealthMap() {
    }

    @Override
    public String GUID() {
        return "LessHealthMap";
    }

    @Override
    public float Min() {
        return -10;
    }

    @Override
    public float Max() {
        return -40;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Multi;
    }

    @Override
    public Stat GetBaseStat() {
        return new Health();
    }

}