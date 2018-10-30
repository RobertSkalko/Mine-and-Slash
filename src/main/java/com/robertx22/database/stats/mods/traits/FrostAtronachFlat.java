package com.robertx22.database.stats.mods.traits;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.mods.BaseTraitMod;
import com.robertx22.database.stats.types.traits.atronachs.FrostAtronach;
import com.robertx22.stats.Stat;

@StatModAnot
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