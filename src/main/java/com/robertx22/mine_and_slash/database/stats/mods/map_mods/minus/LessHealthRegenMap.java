package com.robertx22.mine_and_slash.database.stats.mods.map_mods.minus;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LessHealthRegenMap extends StatMod {

    public LessHealthRegenMap() {
    }

    @Override
    public String GUID() {
        return "less_health_regen_map";
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
        return HealthRegen.getInstance();
    }

}