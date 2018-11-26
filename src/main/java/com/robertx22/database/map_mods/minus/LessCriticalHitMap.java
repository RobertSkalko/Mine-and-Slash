package com.robertx22.database.map_mods.minus;

import com.robertx22.database.stats.types.offense.CriticalHit;
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

	@Override
	public Stat GetBaseStat() {
		return new CriticalHit();
	}

}