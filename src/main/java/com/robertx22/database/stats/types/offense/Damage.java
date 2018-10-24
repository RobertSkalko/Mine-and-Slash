package com.robertx22.database.stats.types.offense;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class Damage extends Stat {

	public Damage() {
		this.StatMinimum = 1;
	}

	@Override
	public String Name() {
		return "Damage";
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
