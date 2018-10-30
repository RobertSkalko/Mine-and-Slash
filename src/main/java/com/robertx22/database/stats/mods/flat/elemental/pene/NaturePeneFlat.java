package com.robertx22.database.stats.mods.flat.elemental.pene;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.types.elementals.pene.NaturePene;
import com.robertx22.stats.Stat;

@StatModAnot
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
