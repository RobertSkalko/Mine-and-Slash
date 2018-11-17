package com.robertx22.database.stats.mods.flat.elemental.dmg;

import com.robertx22.database.stats.types.elementals.damage.FireDamage;
import com.robertx22.stats.Stat;

public class FireDamageFlat extends BaseEleDmgFlat {

	public FireDamageFlat() {
	}

	@Override
	public String GUID() {
		return "FireDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new FireDamage();
	}

}
