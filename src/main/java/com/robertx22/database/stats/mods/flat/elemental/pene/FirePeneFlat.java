package com.robertx22.database.stats.mods.flat.elemental.pene;

import com.robertx22.database.stats.types.elementals.pene.FirePene;
import com.robertx22.stats.Stat;

public class FirePeneFlat extends BaseElePeneFlat {

	public FirePeneFlat() {
	}

	@Override
	public String GUID() {
		return "FirePeneFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new FirePene();
	}

}
