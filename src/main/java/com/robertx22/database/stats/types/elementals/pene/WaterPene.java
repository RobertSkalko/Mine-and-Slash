package com.robertx22.database.stats.types.elementals.pene;

import com.robertx22.enums.Elements;
import com.robertx22.stats.Stat;

public class WaterPene extends Stat {
	public WaterPene() {
	}

	@Override
	public String Name() {
		return "Water Penetration";
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
