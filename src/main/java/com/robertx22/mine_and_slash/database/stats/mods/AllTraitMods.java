package com.robertx22.mine_and_slash.database.stats.mods;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.BaseTrait;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;

public class AllTraitMods extends StatMod {

    public AllTraitMods(BaseTrait stat) {
        this.GUID = stat.formattedGUID() + "_flat";
        this.baseStat = stat;
        this.weight = stat.Weight();
    }

    private String GUID;
    private Stat baseStat;
    private int weight;

    @Override

    public List<StatMod> getAllSizeVariations() {
        return Arrays.asList(this);// traits dont need variations
    }

    @Override
    public float Min() {
        return 1;
    }

    @Override
    public float Max() {
        return 1;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
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
