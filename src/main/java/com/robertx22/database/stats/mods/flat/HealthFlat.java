package com.robertx22.database.stats.mods.flat;

import com.robertx22.database.stats.types.Health;
import com.robertx22.enumclasses.StatTypes;
import com.robertx22.interfaces.IWeighted;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;

public class HealthFlat extends StatMod {

	public HealthFlat() {
	}

	@Override
	public String GUID() {
		return "HealthFlat";
	}

	@Override
	public int Min() {
		return 5;
	}

	@Override
	public int Max() {
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

	@Override
	public int Weight() {
		return IWeighted.NormalWeight;
	}
}
