package com.robertx22.database.stats.types.elementals.damage;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class NatureDamage extends Stat {
	public NatureDamage() {
	}

	@Override
	public String Name() {
		return "Nature Damage";
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
