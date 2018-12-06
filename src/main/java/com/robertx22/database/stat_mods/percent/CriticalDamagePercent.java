package com.robertx22.database.stat_mods.percent;

import com.robertx22.database.stat_types.offense.CriticalDamage;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public class CriticalDamagePercent extends StatMod {

	public CriticalDamagePercent() {
	}

	@Override
	public String GUID() {
		return "CriticalDamagePercent";
	}

	@Override
	public float Min() {
		return 4;

	}

	@Override
	public float Max() {
		return 15;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Percent;
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
