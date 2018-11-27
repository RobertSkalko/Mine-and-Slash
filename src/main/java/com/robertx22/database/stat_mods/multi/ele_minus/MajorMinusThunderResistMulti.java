package com.robertx22.database.stat_mods.multi.ele_minus;

import com.robertx22.database.stat_types.elementals.resist.ThunderResist;
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