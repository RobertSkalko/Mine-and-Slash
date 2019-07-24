package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.HealthRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class HealthRegenFlat extends StatMod {

    public HealthRegenFlat() {
    }

    @Override
    public String GUID() {
        return "HealthRegenFlat";
    }

    @Override
    public float Min() {
        return 1;
    }

    @Override
    public float Max() {
        return 2.5F;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new HealthRegen();
    }

}
