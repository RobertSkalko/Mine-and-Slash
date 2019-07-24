package com.robertx22.mine_and_slash.database.stats.stat_mods.percent;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.Lifesteal;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LifestealPercent extends StatMod {

	public LifestealPercent() {
	}

	@Override
	public String GUID() {
		return "LifestealPercent";
	}

	@Override
	public float Min() {
		return 10;
	}

	@Override
	public float Max() {
		return 25;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Percent;
	}

	@Override
	public Stat GetBaseStat() {
		return new Lifesteal();
	}

}
