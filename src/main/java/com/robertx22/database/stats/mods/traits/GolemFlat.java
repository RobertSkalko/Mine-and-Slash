package com.robertx22.database.stats.mods.traits;

import com.robertx22.database.stats.mods.BaseTraitMod;
import com.robertx22.database.stats.types.traits.Golem;
import com.robertx22.stats.Stat;

public class GolemFlat extends BaseTraitMod {

	public GolemFlat() {
	}

	@Override
	public String GUID() {
		return "GolemFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new Golem();
	}

}
