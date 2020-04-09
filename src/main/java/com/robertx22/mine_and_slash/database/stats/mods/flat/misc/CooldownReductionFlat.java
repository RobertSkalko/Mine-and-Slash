package com.robertx22.mine_and_slash.database.stats.mods.flat.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.ReducedCooldownStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class CooldownReductionFlat extends StatMod {

    @Override
    public Stat GetBaseStat() {
        return ReducedCooldownStat.getInstance();
    }

    @Override
    public float Min() {
        return 4;
    }

    @Override
    public float Max() {
        return 8;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }
}
