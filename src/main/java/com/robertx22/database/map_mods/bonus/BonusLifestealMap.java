package com.robertx22.database.map_mods.bonus;

import com.robertx22.database.stat_types.resources.Lifesteal;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class BonusLifestealMap extends StatMod {

	public BonusLifestealMap() {
	}

	@Override
	public String GUID() {
		return "BonusLifestealMap";
	}

	@Override
	public float Min() {
		return 15;
	}

	@Override
	public float Max() {
		return 40;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public Stat GetBaseStat() {
		return new Lifesteal();
	}

}