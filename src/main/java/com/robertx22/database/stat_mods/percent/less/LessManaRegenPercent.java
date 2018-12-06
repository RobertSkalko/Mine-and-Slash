package com.robertx22.database.stat_mods.percent.less;

import com.robertx22.database.stat_types.resources.ManaRegen;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class LessManaRegenPercent extends StatMod {

    public LessManaRegenPercent() {
    }

    @Override
    public String GUID() {
	return "LessManaRegenPercent";

    }

    @Override
    public float Min() {
	return -10;
    }

    @Override
    public float Max() {
	return -20;
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