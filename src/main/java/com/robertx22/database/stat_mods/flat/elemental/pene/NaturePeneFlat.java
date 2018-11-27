package com.robertx22.database.stat_mods.flat.elemental.pene;

import com.robertx22.database.stat_types.elementals.pene.NaturePene;
import com.robertx22.stats.Stat;

public class NaturePeneFlat extends BaseElePeneFlat {

	public NaturePeneFlat() {
	}

	@Override
	public String GUID() {
		return "NaturePeneFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new NaturePene();
	}

}
