package com.robertx22.database.stat_mods.percent.less;

import com.robertx22.database.stat_types.resources.Lifesteal;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class LessLifestealPercent extends StatMod {

    public LessLifestealPercent() {
    }

    @Override
    public String GUID() {
	return "LessLifestealPercent";

    }

    @Override
    public int Min() {
	return -10;
    }

    @Override
    public int Max() {
	return -20;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
	return new Lifesteal();
    }

}