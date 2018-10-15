package com.robertx22.database.stats.types;

import com.robertx22.enumclasses.Elements;
import com.robertx22.stats.UsableStat;

public class Armor extends UsableStat {

	public Armor() {
	}

	@Override
	public String Name() {
		return "Armor";
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
		return true;
	}

	@Override
	public float MaximumPercent() {
		return 0.75F;
	}

	@Override
	public int AverageStat() {
		return 50;
	}

}
