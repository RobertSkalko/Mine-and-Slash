package com.robertx22.database.stat_mods.flat.resources;

import com.robertx22.database.stat_types.resources.HealthRegen;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class HealthRegenFlat extends StatMod {

	public HealthRegenFlat() {
	}

	@Override
	public String GUID() {
		return "HealthRegenFlat";
	}

	@Override
	public float Min() {
		return 1;
	}

	@Override
	public float Max() {
		return 3;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public Stat GetBaseStat() {
		return new HealthRegen();
	}

}
