package com.robertx22.database.stat_mods.flat;

import com.robertx22.database.stat_types.offense.CriticalHit;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public class CriticalHitFlat extends StatMod {

	public CriticalHitFlat() {
	}

	@Override
	public String GUID() {
		return "CriticalHitFlat";
	}

	@Override
	public float Min() {
		return 2;

	}

	@Override
	public float Max() {
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
		return IWeighted.UncommonWeight;
	}

}
