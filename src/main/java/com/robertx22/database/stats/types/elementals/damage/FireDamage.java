package com.robertx22.database.stats.types.elementals.damage;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class FireDamage extends Stat {
	public static String GUID = "Fire Damage";

	public FireDamage() {
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
		return Elements.Fire;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}
