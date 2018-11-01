package com.robertx22.database.stats.mods.flat.resources;

import com.robertx22.database.stats.types.resources.Mana;
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
		return new Mana();
	}

}
