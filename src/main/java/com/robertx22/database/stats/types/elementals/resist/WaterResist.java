package com.robertx22.database.stats.types.elementals.resist;

import com.robertx22.stats.UsableStat;
import com.robertx22.uncommon.enumclasses.Elements;

public class WaterResist extends UsableStat {
	@Override
	public float MaximumPercent() {
		return 0.75F;
	}

	@Override
	public int AverageStat() {
		return 35;
	}

	public WaterResist() {
	}

	@Override
	public String Name() {
		return "Water Resist";
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return Elements.Water;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}
