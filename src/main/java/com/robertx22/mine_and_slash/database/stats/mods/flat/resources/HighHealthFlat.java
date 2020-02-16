package com.robertx22.mine_and_slash.database.stats.mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class HighHealthFlat extends StatMod {

    @Override
    public float Min() {
        return 10;
    }

    @Override
    public float Max() {
        return 20;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return Health.getInstance();
    }

}
