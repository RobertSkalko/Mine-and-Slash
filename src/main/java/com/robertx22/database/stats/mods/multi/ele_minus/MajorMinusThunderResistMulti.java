package com.robertx22.database.stats.mods.multi.ele_minus;

import com.robertx22.database.stats.types.elementals.resist.ThunderResist;
import com.robertx22.stats.Stat;

public class MajorMinusThunderResistMulti extends BaseMajorEleResistMinus {

	public MajorMinusThunderResistMulti() {
	}

	@Override
	public String GUID() {
		return "MajorMinusThunderResistMulti";
	}

	@Override
	public Stat GetBaseStat() {
		return new ThunderResist();
	}

}