package com.robertx22.database.stats.mods.flat;

import com.robertx22.database.stats.types.CriticalDamage;
import com.robertx22.enums.StatTypes;
import com.robertx22.interfaces.IWeighted;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;

public class CriticalDamageFlat extends StatMod {

	public CriticalDamageFlat() {
	}

	@Override
	public String GUID() {
		return "CriticalDamageFlat";
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
		return new CriticalDamage();
	}

	@Override
	public int Weight() {
		return IWeighted.NormalWeight;
	}
}
