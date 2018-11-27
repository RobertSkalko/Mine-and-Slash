package com.robertx22.database.stat_mods.traits;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.bad_and_good.Barbarian;
import com.robertx22.stats.Stat;

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
