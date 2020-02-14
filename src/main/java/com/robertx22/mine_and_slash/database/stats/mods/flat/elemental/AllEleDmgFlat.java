package com.robertx22.mine_and_slash.database.stats.mods.flat.elemental;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.elementals.all_damage.AllEleDmg;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class AllEleDmgFlat extends StatMod {

    @Override
    public Stat GetBaseStat() {
        return new AllEleDmg();
    }

    @Override
    public float Min() {
        return 4;
    }

    @Override
    public float Max() {
        return 10;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

}
