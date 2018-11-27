package com.robertx22.database.stat_mods.flat.elemental.resist;

import com.robertx22.database.stat_types.elementals.resist.ThunderResist;
import com.robertx22.stats.Stat;

public class ThunderResistFlat extends BaseEleResistFlat {

	public ThunderResistFlat() {
	}

	@Override
	public String GUID() {
		return "ThunderResistFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new ThunderResist();
	}

}
