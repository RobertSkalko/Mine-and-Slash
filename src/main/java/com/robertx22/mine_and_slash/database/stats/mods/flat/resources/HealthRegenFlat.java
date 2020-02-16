package com.robertx22.mine_and_slash.database.stats.mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class HealthRegenFlat extends StatMod {

    public HealthRegenFlat() {
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
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return HealthRegen.getInstance();
    }

}
