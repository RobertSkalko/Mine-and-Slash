package com.robertx22.database.map_mods.minus.all_ele_dmg;

import com.robertx22.database.stat_types.elementals.all_damage.AllNatureDamage;
import com.robertx22.stats.Stat;

public class LessAllNatureDamageMap extends BaseAllEleDmgMap {

	@Override
	public Stat GetBaseStat() {
		return new AllNatureDamage();
	}

	@Override
	public String GUID() {
		return "LessAllNatureDamageMap";
	}

}
