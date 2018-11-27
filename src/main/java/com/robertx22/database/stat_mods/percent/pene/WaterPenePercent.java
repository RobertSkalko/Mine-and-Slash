package com.robertx22.database.stat_mods.percent.pene;

import com.robertx22.database.stat_types.elementals.pene.WaterPene;
import com.robertx22.stats.Stat;

public class WaterPenePercent extends BaseElePenePercent {

	@Override
	public Stat GetBaseStat() {
		return new WaterPene();
	}

	@Override
	public String GUID() {
		return "WaterPenePercent";
	}

}
