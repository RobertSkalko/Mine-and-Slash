package com.robertx22.mine_and_slash.database.stats.mods.percent;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.defense.BlockStrength;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class BlockStrengthPercent extends StatMod {

    @Override
    public float Min() {
        return 10;
    }

    @Override
    public float Max() {
        return 30;

    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
        return new BlockStrength();
    }

}
