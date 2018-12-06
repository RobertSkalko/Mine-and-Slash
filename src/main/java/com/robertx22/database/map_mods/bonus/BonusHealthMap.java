package com.robertx22.database.map_mods.bonus;

import com.robertx22.database.stat_types.resources.Health;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class BonusHealthMap extends StatMod {

	public BonusHealthMap() {
	}

	@Override
	public String GUID() {
		return "BonusHealthMap";
	}

	@Override
	public float Min() {
		return 20;
	}

	@Override
	public float Max() {
		return 50;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public Stat GetBaseStat() {
		return new Health();
	}

}