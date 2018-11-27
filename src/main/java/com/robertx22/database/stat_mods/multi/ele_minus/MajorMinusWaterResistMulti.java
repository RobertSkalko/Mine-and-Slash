package com.robertx22.database.stat_mods.multi.ele_minus;

import com.robertx22.database.stat_types.elementals.resist.WaterResist;
import com.robertx22.stats.Stat;

public class MajorMinusWaterResistMulti extends BaseMajorEleResistMinus {

	public MajorMinusWaterResistMulti() {
	}

	@Override
	public String GUID() {
		return "MajorMinusWaterResistMulti";
	}

	@Override
	public Stat GetBaseStat() {
		return new WaterResist();
	}

}
