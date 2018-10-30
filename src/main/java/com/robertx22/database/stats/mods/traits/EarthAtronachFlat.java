package com.robertx22.database.stats.mods.traits;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.mods.BaseTraitMod;
import com.robertx22.database.stats.types.traits.atronachs.EarthAtronach;
import com.robertx22.stats.Stat;

@StatModAnot
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