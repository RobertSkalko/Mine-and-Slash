package com.robertx22.database.stats.mods.flat;

import com.robertx22.database.stats.types.Armor;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public class ArmorFlat extends StatMod {

	public ArmorFlat() {
	}

	@Override
	public String GUID() {
		return "ArmorFlat";
	}

	@Override
	public int Min() {
		return 2;
	}

	@Override
	public int Max() {
		return 10;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public Stat GetBaseStat() {
		return new Armor();
	}

	@Override
	public int Weight() {
		return IWeighted.NormalWeight;
	}

}
