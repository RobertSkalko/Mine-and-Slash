package com.robertx22.database.stats.mods.flat;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.types.offense.Lifesteal;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

@StatModAnot
public class LifestealFlat extends StatMod {

	public LifestealFlat() {
	}

	@Override
	public String GUID() {
		return "LifestealFlat";
	}

	@Override
	public int Min() {
		return 1;
	}

	@Override
	public int Max() {
		return 5;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public Stat GetBaseStat() {
		return new Lifesteal();
	}

	@Override
	public int Weight() {
		return IWeighted.NormalWeight;
	}

}
