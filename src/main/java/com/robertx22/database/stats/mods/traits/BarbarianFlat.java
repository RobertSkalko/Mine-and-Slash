package com.robertx22.database.stats.mods.traits;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.mods.BaseTraitMod;
import com.robertx22.database.stats.types.traits.bad_and_good.Barbarian;
import com.robertx22.stats.Stat;

@StatModAnot
public class BarbarianFlat extends BaseTraitMod {

	public BarbarianFlat() {
	}

	@Override
	public String GUID() {
		return "Barbarian";
	}

	@Override
	public Stat GetBaseStat() {
		return new Barbarian();
	}

}
