package com.robertx22.mine_and_slash.database.stats.mods.multi.offence;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class PhysicalDamageMulti extends StatMod {

    @Override
    public float Min() {
        return 5;
    }

    @Override
    public float Max() {
        return 15;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Multi;
    }

    @Override
    public Stat GetBaseStat() {
        return PhysicalDamage.getInstance();
    }

}