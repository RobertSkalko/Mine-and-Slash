package com.robertx22.database.stat_mods.traits.atronachs;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.atronachs.ThunderAtronach;
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