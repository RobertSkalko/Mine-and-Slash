package com.robertx22.mine_and_slash.database.stats.stat_mods;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.BaseTrait;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class AllTraitMods extends StatMod {

    public AllTraitMods(BaseTrait stat) {
        this.GUID = stat.formattedGUID() + "flat";
        this.baseStat = stat;
        this.weight = stat.Weight();
    }

    private String GUID;
    private Stat baseStat;
    private int weight;

    @Override
    public float Min() {
        return 1;
    }

    @Override
    public float Max() {
        return 1;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Stat GetBaseStat() {
        return baseStat;
    }

    @Override
    public int Weight() {
        return weight;
    }
}
