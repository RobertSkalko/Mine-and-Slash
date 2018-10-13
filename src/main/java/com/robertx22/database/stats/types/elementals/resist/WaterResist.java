package com.robertx22.database.stats.types.elementals.resist;

import com.robertx22.enums.Elements;
import com.robertx22.stats.Stat;

public class WaterResist extends Stat {
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
