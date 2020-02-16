package com.robertx22.mine_and_slash.database.stats.mods.percent;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class HealthPercent extends StatMod {

    public HealthPercent() {
    }

    @Override
    public float Min() {
        return 2;
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
        return Health.getInstance();
    }

}
