package com.robertx22.database.stat_mods.flat.elemental.pene;

import com.robertx22.database.stat_types.elementals.pene.WaterPene;
import com.robertx22.stats.Stat;

public class WaterPeneFlat extends BaseElePeneFlat {

	public WaterPeneFlat() {
	}

	@Override
	public String GUID() {
		return "WaterPeneFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new WaterPene();
	}

}
