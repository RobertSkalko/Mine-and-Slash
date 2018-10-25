package com.robertx22.database.stats.mods.traits;

import com.robertx22.database.stats.mods.BaseTraitMod;
import com.robertx22.database.stats.types.traits.atronachs.FireAtronach;
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