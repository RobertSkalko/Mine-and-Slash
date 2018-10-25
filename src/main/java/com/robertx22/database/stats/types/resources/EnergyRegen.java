package com.robertx22.database.stats.types.resources;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class EnergyRegen extends Stat {
	public static String GUID = "Energy Regen";

	public EnergyRegen() {
		this.StatMinimum = 5;
	}

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public boolean ScalesToLevel() {
		return false;
	}

	@Override
	public Elements Element() {
		return null;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}
