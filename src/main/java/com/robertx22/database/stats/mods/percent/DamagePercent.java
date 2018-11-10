package com.robertx22.database.stats.mods.percent;

import com.robertx22.database.stats.types.offense.PhysicalDamage;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class DamagePercent extends StatMod {

	public DamagePercent() {
	}

	@Override
	public String GUID() {
		return "DamagePercent";
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
		return StatTypes.Percent;
	}

	@Override
	public Stat GetBaseStat() {
		return new PhysicalDamage();
	}

}
