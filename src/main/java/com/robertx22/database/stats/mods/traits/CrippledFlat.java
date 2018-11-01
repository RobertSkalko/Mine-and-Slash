package com.robertx22.database.stats.mods.traits;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.mods.BaseTraitMod;
import com.robertx22.database.stats.types.traits.bad_ones.Crippled;
import com.robertx22.stats.Stat;

@StatModAnot
public class CrippledFlat extends BaseTraitMod {

	public CrippledFlat() {
	}

	@Override
	public String GUID() {
		return "CrippledFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new Crippled();
	}

	@Override
	public int Weight() {
		return 7500;
	}
}
