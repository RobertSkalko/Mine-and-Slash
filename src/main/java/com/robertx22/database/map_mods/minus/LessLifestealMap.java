package com.robertx22.database.map_mods.minus;

import com.robertx22.database.stat_types.resources.Lifesteal;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class LessLifestealMap extends StatMod {

	public LessLifestealMap() {
	}

	@Override
	public String GUID() {
		return "LessLifestealMap";
	}

	@Override
	public float Min() {
		return -30;
	}

	@Override
	public float Max() {
		return -80;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Multi;
	}

	@Override
	public Stat GetBaseStat() {
		return new Lifesteal();
	}

}