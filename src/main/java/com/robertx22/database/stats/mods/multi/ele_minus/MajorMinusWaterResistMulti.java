package com.robertx22.database.stats.mods.multi.ele_minus;

import com.robertx22.database.stats.types.elementals.resist.WaterResist;
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
