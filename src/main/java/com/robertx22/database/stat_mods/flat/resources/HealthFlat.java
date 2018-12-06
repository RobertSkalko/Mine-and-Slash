package com.robertx22.database.stat_mods.flat.resources;

import com.robertx22.database.stat_types.resources.Health;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class HealthFlat extends StatMod {

	public HealthFlat() {
	}

	@Override
	public String GUID() {
		return "HealthFlat";
	}

	@Override
	public float Min() {
		return 5;
	}

	@Override
	public float Max() {
		return 15;
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
