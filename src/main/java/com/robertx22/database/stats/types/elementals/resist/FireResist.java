package com.robertx22.database.stats.types.elementals.resist;

import com.robertx22.enums.Elements;
import com.robertx22.stats.Stat;

public class FireResist extends Stat {
	public FireResist() {
	}

	@Override
	public String Name() {
		return "Fire Resist";
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return Elements.Fire;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}
