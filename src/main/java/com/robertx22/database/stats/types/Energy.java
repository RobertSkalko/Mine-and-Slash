package com.robertx22.database.stats.types;

import com.robertx22.stats.FillableStat;
import com.robertx22.uncommon.enumclasses.Elements;

public class Energy extends FillableStat {
	public Energy() {
		this.StatMinimum = 100;
	}

	@Override
	public String Name() {
		return "Energy";
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
