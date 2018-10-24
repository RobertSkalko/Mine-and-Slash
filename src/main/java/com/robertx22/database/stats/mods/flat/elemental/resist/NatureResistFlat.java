package com.robertx22.database.stats.mods.flat.elemental.resist;

import com.robertx22.database.stats.types.elementals.resist.NatureResist;
import com.robertx22.stats.Stat;

public class NatureResistFlat extends BaseEleResistFlat {

	public NatureResistFlat() {
	}

	@Override
	public String GUID() {
		return "NatureResistFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new NatureResist();
	}

}
