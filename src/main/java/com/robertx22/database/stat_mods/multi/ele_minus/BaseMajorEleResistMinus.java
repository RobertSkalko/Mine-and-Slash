package com.robertx22.database.stat_mods.multi.ele_minus;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class BaseMajorEleResistMinus extends StatMod {

	public BaseMajorEleResistMinus() {
	}

	@Override
	public int Min() {
		return -20;
	}

	@Override
	public int Max() {
		return -50;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Multi;
	}

}
