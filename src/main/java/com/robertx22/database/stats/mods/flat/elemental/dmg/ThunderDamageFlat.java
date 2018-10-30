package com.robertx22.database.stats.mods.flat.elemental.dmg;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.types.elementals.damage.ThunderDamage;
import com.robertx22.stats.Stat;

@StatModAnot
public class ThunderDamageFlat extends BaseEleDmgFlat {

	public ThunderDamageFlat() {
	}

	@Override
	public String GUID() {
		return "ThunderDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new ThunderDamage();
	}

}
