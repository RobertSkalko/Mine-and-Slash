package com.robertx22.mine_and_slash.database.stats.stat_mods.percent;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

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
