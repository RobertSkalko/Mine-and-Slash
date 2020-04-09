package com.robertx22.mine_and_slash.database.stats.mods.flat.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.ReducedManaCost;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class ReducedManaCostFlat extends StatMod {

    @Override
    public Stat GetBaseStat() {
        return ReducedManaCost.getInstance();
    }

    @Override
    public float Min() {
        return 3;
    }

    @Override
    public float Max() {
        return 6;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }
}

