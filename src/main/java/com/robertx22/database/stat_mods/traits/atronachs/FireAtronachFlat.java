package com.robertx22.database.stat_mods.traits.atronachs;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.atronachs.FireAtronach;
import com.robertx22.stats.Stat;

public class FireAtronachFlat extends BaseTraitMod {

	public FireAtronachFlat() {
	}

	@Override
	public String GUID() {
		return "FireAtronachFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new FireAtronach();
	}

}