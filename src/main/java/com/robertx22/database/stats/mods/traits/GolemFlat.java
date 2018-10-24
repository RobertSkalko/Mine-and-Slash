package com.robertx22.database.stats.mods.traits;

import com.robertx22.database.stats.types.traits.Golem;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public class GolemFlat extends StatMod {

	public GolemFlat() {
	}

	@Override
	public String GUID() {
		return "GolemFlat";
	}

	@Override
	public int Min() {
		return 1;
	}

	@Override
	public int Max() {
		return 1;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public Stat GetBaseStat() {
		return new Golem();
	}

	@Override
	public int Weight() {
		return IWeighted.NormalWeight;
	}

}
