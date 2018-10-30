package com.robertx22.database.stats.mods.flat;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.types.offense.CriticalHit;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

@StatModAnot
public class CriticalHitFlat extends StatMod {

	public CriticalHitFlat() {
	}

	@Override
	public String GUID() {
		return "CriticalHitFlat";
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
		return new CriticalHit();
	}

	@Override
	public int Weight() {
		return IWeighted.NormalWeight;
	}

}
