package com.robertx22.database.map_mods.bonus.ele_res;

import com.robertx22.database.map_mods.bases.BonusEleResistBase;
import com.robertx22.database.stat_types.elementals.resist.NatureResist;
import com.robertx22.stats.Stat;

public class BonusNatureResistMap extends BonusEleResistBase {

	@Override
	public String GUID() {
		return "BonusNatureResistMap";
	}

	@Override
	public Stat GetBaseStat() {
		return new NatureResist();
	}

}
