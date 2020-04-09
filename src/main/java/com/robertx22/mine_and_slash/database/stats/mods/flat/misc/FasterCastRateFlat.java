package com.robertx22.mine_and_slash.database.stats.mods.flat.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.FasterCastRate;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class FasterCastRateFlat extends StatMod {

    @Override
    public Stat GetBaseStat() {
        return FasterCastRate.getInstance();
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
