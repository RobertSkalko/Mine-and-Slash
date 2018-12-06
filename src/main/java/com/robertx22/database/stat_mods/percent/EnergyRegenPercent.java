package com.robertx22.database.stat_mods.percent;

import com.robertx22.database.stat_types.resources.EnergyRegen;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class EnergyRegenPercent extends StatMod {

	public EnergyRegenPercent() {
	}

	@Override
	public String GUID() {
		return "EnergyRegenPercent";
	}

	@Override
	public float Min() {
		return 7;
	}

	@Override
	public float Max() {
		return 20;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Percent;
	}

	@Override
	public Stat GetBaseStat() {
		return new EnergyRegen();
	}

}
