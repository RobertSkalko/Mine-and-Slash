package com.robertx22.database.stats.types;

import com.robertx22.stats.FillableStat;
import com.robertx22.uncommon.enumclasses.Elements;

public class Mana extends FillableStat {
	public Mana() {
		this.StatMinimum = 50;
	}

	@Override
	public String Name() {
		return "Mana";
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
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
