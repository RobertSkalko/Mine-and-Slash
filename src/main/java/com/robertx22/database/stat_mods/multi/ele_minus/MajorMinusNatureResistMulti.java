package com.robertx22.database.stat_mods.multi.ele_minus;

import com.robertx22.database.stat_types.elementals.resist.NatureResist;
import com.robertx22.stats.Stat;

public class MajorMinusNatureResistMulti extends BaseMajorEleResistMinus {

	public MajorMinusNatureResistMulti() {
	}

	@Override
	public String GUID() {
		return "MajorMinusNatureResistMulti";
	}

	@Override
	public Stat GetBaseStat() {
		return new NatureResist();
	}

}