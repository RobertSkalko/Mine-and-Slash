package com.robertx22.database.stats.mods.flat.elemental.dmg;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.types.elementals.damage.WaterDamage;
import com.robertx22.stats.Stat;

@StatModAnot
public class WaterDamageFlat extends BaseEleDmgFlat {

	public WaterDamageFlat() {
	}

	@Override
	public String GUID() {
		return "WaterDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new WaterDamage();
	}

}
