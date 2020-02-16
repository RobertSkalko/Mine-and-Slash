package com.robertx22.mine_and_slash.database.stats.mods.percent.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class PhysicalDamagePercent extends StatMod {

    public PhysicalDamagePercent() {
    }

    @Override
    public float Min() {
        return 3;
    }

    @Override
    public float Max() {
        return 8;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
        return PhysicalDamage.getInstance();
    }

}
