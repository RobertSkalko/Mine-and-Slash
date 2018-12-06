package com.robertx22.database.stat_mods.percent.pene;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class BaseElePenePercent extends StatMod {

    public BaseElePenePercent() {
    }

    @Override
    public float Min() {
	return 10;
    }

    @Override
    public float Max() {
	return 25;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Percent;
    }

}
