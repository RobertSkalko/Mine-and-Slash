package com.robertx22.database.stats.types;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class EnergyRegen extends Stat {

	public EnergyRegen() {
		this.StatMinimum = 5;
	}

	@Override
	public String Name() {
		return "Energy Regen";
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
