package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class EnergyRegenFlat extends StatMod {

    public EnergyRegenFlat() {
    }

    @Override
    public String GUID() {
        return "EnergyRegenFlat";
    }

    @Override
    public float Min() {
        return 1.25F;
    }

    @Override
    public float Max() {
        return 3;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new EnergyRegen();
    }

}
