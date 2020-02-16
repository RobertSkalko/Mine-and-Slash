package com.robertx22.mine_and_slash.database.stats.mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class HighEnergyRegenFlat extends EnergyRegenFlat {

    @Override
    public float Min() {
        return super.Min() * 2;
    }

    @Override
    public float Max() {
        return super.Max() * 2;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return EnergyRegen.getInstance();
    }

}
