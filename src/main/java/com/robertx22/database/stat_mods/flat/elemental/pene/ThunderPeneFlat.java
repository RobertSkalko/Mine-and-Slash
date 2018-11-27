package com.robertx22.database.stat_mods.flat.elemental.pene;

import com.robertx22.database.stat_types.elementals.pene.ThunderPene;
import com.robertx22.stats.Stat;

public class ThunderPeneFlat extends BaseElePeneFlat {

	public ThunderPeneFlat() {
	}

	@Override
	public String GUID() {
		return "ThunderPeneFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new ThunderPene();
	}

}
