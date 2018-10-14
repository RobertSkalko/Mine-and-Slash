package com.robertx22.database.stats.types.elementals.resist;

import com.robertx22.enums.Elements;
import com.robertx22.stats.UsableStat;

public class FireResist extends UsableStat {
	@Override
	public float MaximumPercent() {
		return 0.75F;
	}

	@Override
	public int AverageStat() {
		return 35;
	}

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
