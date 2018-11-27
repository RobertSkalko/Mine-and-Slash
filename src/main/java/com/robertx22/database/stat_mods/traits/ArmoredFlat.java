package com.robertx22.database.stat_mods.traits;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.Armored;
import com.robertx22.stats.Stat;

public class ArmoredFlat extends BaseTraitMod {

	public ArmoredFlat() {
	}

	@Override
	public String GUID() {
		return "ArmoredFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new Armored();
	}

}
