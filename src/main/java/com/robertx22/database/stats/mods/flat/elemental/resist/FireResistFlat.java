package com.robertx22.database.stats.mods.flat.elemental.resist;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.types.elementals.resist.FireResist;
import com.robertx22.stats.Stat;

@StatModAnot
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
