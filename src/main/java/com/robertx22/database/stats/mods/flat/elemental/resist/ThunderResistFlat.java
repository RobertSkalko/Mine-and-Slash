package com.robertx22.database.stats.mods.flat.elemental.resist;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.types.elementals.resist.ThunderResist;
import com.robertx22.stats.Stat;

@StatModAnot
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
