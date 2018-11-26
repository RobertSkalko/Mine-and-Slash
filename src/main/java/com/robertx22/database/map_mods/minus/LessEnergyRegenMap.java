package com.robertx22.database.map_mods.minus;

import com.robertx22.database.stats.types.resources.EnergyRegen;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class LessEnergyRegenMap extends StatMod {

	public LessEnergyRegenMap() {
	}

	@Override
	public String GUID() {
		return "LessEnergyRegenMap";
	}

	@Override
	public int Min() {
		return -30;
	}

	@Override
	public int Max() {
		return -80;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Multi;
	}

	@Override
	public Stat GetBaseStat() {
		return new EnergyRegen();
	}

}