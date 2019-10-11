package com.robertx22.mine_and_slash.database.stats.mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.Lifesteal;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LifestealFlat extends StatMod {

    public LifestealFlat() {
    }

    @Override
    public String GUID() {
        return "LifestealFlat";
    }

    @Override
    public float Min() {
        return 2;
    }

    @Override
    public float Max() {
        return 5;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return Lifesteal.INSTANCE;
    }

}
