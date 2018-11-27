package com.robertx22.database.stat_mods.multi.ele_minus;

import com.robertx22.database.stat_types.elementals.resist.FireResist;
import com.robertx22.stats.Stat;

public class MajorMinusFireResistMulti extends BaseMajorEleResistMinus {

	public MajorMinusFireResistMulti() {
	}

	@Override
	public String GUID() {
		return "MajorMinusFireResistMulti";
	}

	@Override
	public Stat GetBaseStat() {
		return new FireResist();
	}

}
