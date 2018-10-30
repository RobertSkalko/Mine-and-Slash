package com.robertx22.database.stats.mods.traits;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.mods.BaseTraitMod;
import com.robertx22.database.stats.types.traits.bad_ones.Diseased;
import com.robertx22.stats.Stat;

@StatModAnot
public class DiseasedFlat extends BaseTraitMod {

	public DiseasedFlat() {
	}

	@Override
	public String GUID() {
		return "DiseasedFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new Diseased();
	}

	@Override
	public int Weight() {
		return 10500;
	}
}
