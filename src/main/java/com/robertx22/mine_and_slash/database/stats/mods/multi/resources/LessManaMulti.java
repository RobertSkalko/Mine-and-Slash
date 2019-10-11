package com.robertx22.mine_and_slash.database.stats.mods.multi.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LessManaMulti extends StatMod {

    @Override
    public String GUID() {
        return "LessManaMulti";
    }

    @Override
    public float Min() {
        return -5;
    }

    @Override
    public float Max() {
        return -15;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Multi;
    }

    @Override
    public Stat GetBaseStat() {
        return Mana.INSTANCE;
    }

}