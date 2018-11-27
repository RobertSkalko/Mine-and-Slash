package com.robertx22.database.stat_mods.flat.elemental.resist;

import com.robertx22.database.stat_types.elementals.resist.WaterResist;
import com.robertx22.stats.Stat;

public class WaterResistFlat extends BaseEleResistFlat {

	public WaterResistFlat() {
	}

	@Override
	public String GUID() {
		return "WaterResistFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new WaterResist();
	}

}
