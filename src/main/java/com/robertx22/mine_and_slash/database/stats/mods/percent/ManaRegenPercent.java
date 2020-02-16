package com.robertx22.mine_and_slash.database.stats.mods.percent;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.ManaRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class ManaRegenPercent extends StatMod {

    public ManaRegenPercent() {
    }

    @Override
    public float Min() {
        return 2;
    }

    @Override
    public float Max() {
        return 10;

    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
        return ManaRegen.getInstance();
    }

}
