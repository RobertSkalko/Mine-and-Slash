package com.robertx22.database.stats.mods.traits;

import com.robertx22.database.stats.mods.BaseTraitMod;
import com.robertx22.database.stats.types.traits.Armored;
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
