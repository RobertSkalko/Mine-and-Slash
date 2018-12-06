package com.robertx22.database.stat_mods.flat.resources;

import com.robertx22.database.stat_types.resources.ManaRegen;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class ManaRegenFlat extends StatMod {

	public ManaRegenFlat() {
	}

	@Override
	public String GUID() {
		return "ManaRegenFlat";
	}

	@Override
	public float Min() {
		return 2;
	}

	@Override
	public float Max() {
		return 4;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public Stat GetBaseStat() {
		return new ManaRegen();
	}

}
