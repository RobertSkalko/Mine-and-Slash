package com.robertx22.database.stats.mods.flat.resources;

import com.robertx22.database.stats.types.resources.HealthRegen;
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
	public int Min() {
		return 1;
	}

	@Override
	public int Max() {
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
