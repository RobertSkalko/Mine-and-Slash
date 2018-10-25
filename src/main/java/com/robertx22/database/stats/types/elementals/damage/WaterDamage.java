package com.robertx22.database.stats.types.elementals.damage;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class WaterDamage extends Stat {
	public static String GUID = "Water Damage";

	public WaterDamage() {
	}

	@Override
	public String Name() {
		return GUID;
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
