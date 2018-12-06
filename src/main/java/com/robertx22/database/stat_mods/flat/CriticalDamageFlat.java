package com.robertx22.database.stat_mods.flat;

import com.robertx22.database.stat_types.offense.CriticalDamage;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public class CriticalDamageFlat extends StatMod {

	public CriticalDamageFlat() {

	}

	@Override
	public String GUID() {
		return "CriticalDamageFlat";
	}

	@Override
	public float Min() {
		return 5;

	}

	@Override
	public float Max() {
		return 15;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public Stat GetBaseStat() {
		return new CriticalDamage();
	}

	@Override
	public int Weight() {
		return IWeighted.UncommonWeight;
	}
}
