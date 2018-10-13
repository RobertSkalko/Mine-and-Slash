package com.robertx22.database.stats.types.elementals.damage;

import com.robertx22.enums.Elements;
import com.robertx22.stats.Stat;

public class FireDamage extends Stat {
	public FireDamage() {
	}

	@Override
	public String Name() {
		return "Fire Damage";
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
