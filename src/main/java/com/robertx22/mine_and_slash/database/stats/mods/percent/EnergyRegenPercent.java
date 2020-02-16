package com.robertx22.mine_and_slash.database.stats.mods.percent;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class EnergyRegenPercent extends StatMod {

    public EnergyRegenPercent() {
    }

    @Override
    public float Min() {
        return 7;
    }

    @Override
    public float Max() {
        return 20;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
        return EnergyRegen.getInstance();
    }

}
