package com.robertx22.database.stat_mods.percent.pene;

import com.robertx22.database.stat_types.elementals.pene.NaturePene;
import com.robertx22.stats.Stat;

public class NaturePenePercent extends BaseElePenePercent {

	@Override
	public Stat GetBaseStat() {
		return new NaturePene();
	}

	@Override
	public String GUID() {
		return "NaturePenePercent";
	}

}
