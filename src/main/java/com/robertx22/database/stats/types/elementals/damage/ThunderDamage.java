package com.robertx22.database.stats.types.elementals.damage;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class ThunderDamage extends Stat {
	public ThunderDamage() {
	}

	@Override
	public String Name() {
		return "Thunder Damage";
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
