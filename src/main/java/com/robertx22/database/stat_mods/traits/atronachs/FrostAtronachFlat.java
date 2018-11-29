package com.robertx22.database.stat_mods.traits.atronachs;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.atronachs.FrostAtronach;
import com.robertx22.stats.Stat;

public class FrostAtronachFlat extends BaseTraitMod {

	public FrostAtronachFlat() {
	}

	@Override
	public String GUID() {
		return "FrostAtronachFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new FrostAtronach();
	}

}