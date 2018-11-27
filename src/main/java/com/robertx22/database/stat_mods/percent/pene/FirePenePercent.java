package com.robertx22.database.stat_mods.percent.pene;

import com.robertx22.database.stat_types.elementals.pene.FirePene;
import com.robertx22.stats.Stat;

public class FirePenePercent extends BaseElePenePercent {

	@Override
	public Stat GetBaseStat() {
		return new FirePene();

	}

	@Override
	public String GUID() {
		return "FirePenePercent";
	}

}
