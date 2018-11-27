package com.robertx22.database.stat_mods.traits;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.bad_ones.Crippled;
import com.robertx22.stats.Stat;

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
