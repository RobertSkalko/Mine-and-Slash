package com.robertx22.database.stat_mods.flat.resources;

import com.robertx22.database.stat_types.resources.Mana;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class ManaFlat extends StatMod {

	public ManaFlat() {
	}

	@Override
	public String GUID() {
		return "ManaFlat";
	}

	@Override
	public float Min() {
		return 10;
	}

	@Override
	public float Max() {
		return 25;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public Stat GetBaseStat() {
		return new Mana();
	}

}
