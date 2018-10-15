package com.robertx22.database.stats.types.elementals.pene;

import com.robertx22.enumclasses.Elements;
import com.robertx22.stats.Stat;

public class NaturePene extends Stat {
	public NaturePene() {
	}

	@Override
	public String Name() {
		return "Nature Penetration";
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
