package com.robertx22.database.stat_mods.flat.misc;

import com.robertx22.database.stat_types.misc.BonusExp;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class BonusExpFlat extends StatMod {

	public BonusExpFlat() {
		return;
	}

	@Override
	public String GUID() {
		return "bonusexpflat";
	}

	@Override
	public float Min() {
		return 10;
	}

	@Override
	public float Max() {
		return 25;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public Stat GetBaseStat() {
		return new BonusExp();
	}
}
