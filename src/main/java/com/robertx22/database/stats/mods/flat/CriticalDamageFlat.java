package com.robertx22.database.stats.mods.flat;

import com.robertx22.database.stats.types.CriticalDamage;
import com.robertx22.enums.StatTypes;
import com.robertx22.stats.StatMod;

public class CriticalDamageFlat extends StatMod {

	public CriticalDamageFlat() {
	}

	@Override
	public String BaseClass() {
		return CriticalDamage.class.toString();
	}

	@Override
	public int Min() {
		return 5;

	}

	@Override
	public int Max() {
		return 15;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

}
