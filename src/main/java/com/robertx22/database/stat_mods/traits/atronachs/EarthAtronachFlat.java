package com.robertx22.database.stat_mods.traits.atronachs;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.atronachs.EarthAtronach;
import com.robertx22.stats.Stat;

public class EarthAtronachFlat extends BaseTraitMod {

	public EarthAtronachFlat() {
	}

	@Override
	public String GUID() {
		return "EarthAtronachFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new EarthAtronach();
	}

}