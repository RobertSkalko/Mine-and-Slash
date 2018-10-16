package com.robertx22.database.stats.types;

import com.robertx22.enumclasses.Elements;
import com.robertx22.stats.Stat;

public class Damage extends Stat {

	public Damage() {
		this.StatMinimum = 5;
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
