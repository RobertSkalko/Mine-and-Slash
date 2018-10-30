package com.robertx22.database.stats.mods.flat.elemental.pene;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.types.elementals.pene.ThunderPene;
import com.robertx22.stats.Stat;

@StatModAnot
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
