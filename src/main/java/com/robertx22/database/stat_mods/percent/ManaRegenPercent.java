package com.robertx22.database.stat_mods.percent;

import com.robertx22.database.stat_types.resources.ManaRegen;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class ManaRegenPercent extends StatMod {

    public ManaRegenPercent() {
    }

    @Override
    public String GUID() {
	return "ManaRegenPercent";
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
    public StatTypes Type() {
	return StatTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
	return new ManaRegen();
    }

}
