package com.robertx22.database.stats.types.elementals.resist;

import com.robertx22.enumclasses.Elements;
import com.robertx22.stats.UsableStat;

public class NatureResist extends UsableStat {
	@Override
	public float MaximumPercent() {
		return 0.75F;
	}

	@Override
	public int AverageStat() {
		return 35;
	}

	public NatureResist() {
	}

	@Override
	public String Name() {
		return "Nature Resist";
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return Elements.Nature;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}
