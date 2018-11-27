package com.robertx22.database.stat_mods.flat.elemental.resist;

import com.robertx22.database.stat_types.elementals.resist.FireResist;
import com.robertx22.stats.Stat;

public class FireResistFlat extends BaseEleResistFlat {

	public FireResistFlat() {
	}

	@Override
	public String GUID() {
		return "FireResistFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new FireResist();
	}

}
