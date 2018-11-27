package com.robertx22.database.map_mods.minus.all_ele_dmg;

import com.robertx22.database.stat_types.elementals.all_damage.AllFireDamage;
import com.robertx22.stats.Stat;

public class LessAllFireDamageMap extends BaseAllEleDmgMap {

	@Override
	public Stat GetBaseStat() {
		return new AllFireDamage();
	}

	@Override
	public String GUID() {
		return "LessAllFireDamageMap";
	}

}
