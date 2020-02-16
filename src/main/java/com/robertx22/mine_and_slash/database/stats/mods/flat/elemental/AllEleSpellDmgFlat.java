package com.robertx22.mine_and_slash.database.stats.mods.flat.elemental;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.elementals.all_damage.AllEleSpellDmg;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class AllEleSpellDmgFlat extends StatMod {

    @Override
    public Stat GetBaseStat() {
        return new AllEleSpellDmg();
    }

    @Override
    public float Min() {
        return 5;
    }

    @Override
    public float Max() {
        return 15;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

}
