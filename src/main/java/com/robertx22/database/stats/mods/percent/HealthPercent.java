package com.robertx22.database.stats.mods.percent;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.types.resources.Health;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

@StatModAnot
public class HealthPercent extends StatMod {

	public HealthPercent() {
	}

	@Override
	public String GUID() {
		return "HealthPercent";
	}

	@Override
	public int Min() {
		return 1;
	}

	@Override
	public int Max() {
		return 6;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Percent;
	}

	@Override
	public Stat GetBaseStat() {
		return new Health();
	}

}
