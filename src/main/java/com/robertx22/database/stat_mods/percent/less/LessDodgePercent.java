package com.robertx22.database.stat_mods.percent.less;

import com.robertx22.database.stat_types.defense.Dodge;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class LessDodgePercent extends StatMod {

    public LessDodgePercent() {
    }

    @Override
    public String GUID() {
	return "LessDodgePercent";

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
	return new Dodge();
    }

}