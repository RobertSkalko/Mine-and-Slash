package com.robertx22.database.stat_mods.traits;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.bad_and_good.ClumsyScholar;
import com.robertx22.stats.Stat;

public class ClumsyScholarFlat extends BaseTraitMod {

	public ClumsyScholarFlat() {
	}

	@Override
	public String GUID() {
		return "ClumsyScholar";
	}

	@Override
	public Stat GetBaseStat() {
		return new ClumsyScholar();
	}

}