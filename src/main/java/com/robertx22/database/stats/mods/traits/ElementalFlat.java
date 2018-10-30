package com.robertx22.database.stats.mods.traits;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.mods.BaseTraitMod;
import com.robertx22.database.stats.types.traits.Elemental;
import com.robertx22.stats.Stat;

@StatModAnot
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
