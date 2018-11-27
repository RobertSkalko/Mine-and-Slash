package com.robertx22.database.stat_mods.percent.pene;

import com.robertx22.database.stat_types.elementals.pene.ThunderPene;
import com.robertx22.stats.Stat;

public class ThunderPenePercent extends BaseElePenePercent {

	@Override
	public Stat GetBaseStat() {
		return new ThunderPene();
	}

	@Override
	public String GUID() {
		return "ThunderPenePercent";
	}

}
