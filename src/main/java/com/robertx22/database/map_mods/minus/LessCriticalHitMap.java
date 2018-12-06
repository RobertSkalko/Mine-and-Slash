package com.robertx22.database.map_mods.minus;

import com.robertx22.database.stat_types.offense.CriticalHit;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class LessCriticalHitMap extends StatMod {

	public LessCriticalHitMap() {
	}

	@Override
	public String GUID() {
		return "LessCriticalHitMap";
	}

	@Override
	public float Min() {
		return -20;
	}

	@Override
	public float Max() {
		return -50;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Multi;
	}

	@Override
	public Stat GetBaseStat() {
		return new CriticalHit();
	}

}