package com.robertx22.database.stat_mods.traits;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.Elemental;
import com.robertx22.stats.Stat;

public class ElementalFlat extends BaseTraitMod {

	public ElementalFlat() {
	}

	@Override
	public String GUID() {
		return "ElementalFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new Elemental();
	}

}
