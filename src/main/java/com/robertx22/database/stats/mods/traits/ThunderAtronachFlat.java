package com.robertx22.database.stats.mods.traits;

import com.robertx22.database.stats.mods.BaseTraitMod;
import com.robertx22.database.stats.types.traits.atronachs.ThunderAtronach;
import com.robertx22.stats.Stat;

public class ThunderAtronachFlat extends BaseTraitMod {

	public ThunderAtronachFlat() {
	}

	@Override
	public String GUID() {
		return "ThunderAtronachFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new ThunderAtronach();
	}

}