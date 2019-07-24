package com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.minus;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LessEnergyRegenMap extends StatMod {

    public LessEnergyRegenMap() {
    }

    @Override
    public String GUID() {
        return "LessEnergyRegenMap";
    }

    @Override
    public float Min() {
        return -30;
    }

    @Override
    public float Max() {
        return -80;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Multi;
    }

    @Override
    public Stat GetBaseStat() {
        return new EnergyRegen();
    }

}