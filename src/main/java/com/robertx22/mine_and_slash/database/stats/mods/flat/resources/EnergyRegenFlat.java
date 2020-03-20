package com.robertx22.mine_and_slash.database.stats.mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class EnergyRegenFlat extends StatMod {

    public EnergyRegenFlat() {
    }

    @Override
    public float Min() {
        return 1.5F;
    }

    @Override
    public float Max() {
        return 2F;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return EnergyRegen.getInstance();
    }

}
