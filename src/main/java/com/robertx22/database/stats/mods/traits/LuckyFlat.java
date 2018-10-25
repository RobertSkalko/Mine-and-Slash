package com.robertx22.database.stats.mods.traits;

import com.robertx22.database.stats.mods.BaseTraitMod;
import com.robertx22.database.stats.types.traits.Lucky;
import com.robertx22.stats.Stat;

public class LuckyFlat extends BaseTraitMod {

	public LuckyFlat() {
	}

	@Override
	public String GUID() {
		return "LuckyFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new Lucky();
	}

}
