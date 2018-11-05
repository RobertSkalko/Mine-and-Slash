package com.robertx22.database.stats.mods.flat.resources;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.types.resources.EnergyRegen;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

@StatModAnot
public class EnergyRegenFlat extends StatMod {

	public EnergyRegenFlat() {
	}

	@Override
	public String GUID() {
		return "EnergyRegenFlat";
	}

	@Override
	public int Min() {
		return 2;
	}

	@Override
	public int Max() {
		return 4;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public Stat GetBaseStat() {
		return new EnergyRegen();
	}

}
