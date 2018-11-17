package com.robertx22.database.stats.mods.multi.ele_minus;

import com.robertx22.database.stats.types.elementals.resist.NatureResist;
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