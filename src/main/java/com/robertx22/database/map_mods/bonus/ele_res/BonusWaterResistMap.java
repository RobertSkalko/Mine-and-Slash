package com.robertx22.database.map_mods.bonus.ele_res;

import com.robertx22.database.map_mods.bases.BonusEleResistBase;
import com.robertx22.database.stat_types.elementals.resist.WaterResist;
import com.robertx22.stats.Stat;

public class BonusWaterResistMap extends BonusEleResistBase {

	@Override
	public String GUID() {
		return "BonusWaterResistMap";
	}

	@Override
	public Stat GetBaseStat() {
		return new WaterResist();
	}

}
