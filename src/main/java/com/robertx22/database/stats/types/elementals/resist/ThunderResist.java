package com.robertx22.database.stats.types.elementals.resist;

import com.robertx22.enums.Elements;
import com.robertx22.stats.Stat;

public class ThunderResist extends Stat {
	public ThunderResist() {
	}

	@Override
	public String Name() {
		return "Thunder Resist";
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return Elements.Thunder;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}
