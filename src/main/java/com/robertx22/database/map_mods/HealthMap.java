package com.robertx22.database.map_mods;

import com.robertx22.database.stats.types.resources.Health;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class HealthMap extends StatMod {

	public HealthMap() {
	}

	@Override
	public String GUID() {
		return "HealthMap";
	}

	@Override
	public int Min() {
		return 20;
	}

	@Override
	public int Max() {
		return 50;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public Stat GetBaseStat() {
		return new Health();
	}

}