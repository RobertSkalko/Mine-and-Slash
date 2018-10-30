package com.robertx22.database.stats.mods.flat.elemental.dmg;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.types.elementals.damage.NatureDamage;
import com.robertx22.stats.Stat;

@StatModAnot
public class NatureDamageFlat extends BaseEleDmgFlat {

	public NatureDamageFlat() {
	}

	@Override
	public String GUID() {
		return "NatureDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new NatureDamage();
	}

}
