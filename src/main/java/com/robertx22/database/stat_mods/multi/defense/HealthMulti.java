package com.robertx22.database.stat_mods.multi.defense;

import com.robertx22.database.stat_types.resources.Health;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class HealthMulti extends StatMod {

    @Override
    public String GUID() {
	return "HealthMulti";
    }

    @Override
    public float Min() {
	return 5;
    }

    @Override
    public float Max() {
	return 10;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Multi;
    }

    @Override
    public Stat GetBaseStat() {
	return new Health();
    }

}