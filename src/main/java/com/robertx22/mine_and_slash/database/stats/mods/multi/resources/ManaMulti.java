package com.robertx22.mine_and_slash.database.stats.mods.multi.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class ManaMulti extends StatMod {

    @Override
    public float Min() {
        return 5;
    }

    @Override
    public float Max() {
        return 20;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Multi;
    }

    @Override
    public Stat GetBaseStat() {
        return Mana.getInstance();
    }

}