package com.robertx22.mine_and_slash.database.stats.mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class HighManaFlat extends StatMod {

    @Override
    public float Min() {
        return 8;
    }

    @Override
    public float Max() {
        return 15;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return Mana.getInstance();
    }

}
