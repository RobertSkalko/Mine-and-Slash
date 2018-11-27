package com.robertx22.database.map_mods.minus;

import com.robertx22.database.stat_types.resources.Health;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class LessHealthMap extends StatMod {

	public LessHealthMap() {
	}

	@Override
	public String GUID() {
		return "LessHealthMap";
	}

	@Override
	public int Min() {
		return -25;
	}

	@Override
	public int Max() {
		return -75;
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