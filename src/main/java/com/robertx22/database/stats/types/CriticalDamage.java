package com.robertx22.database.stats.types;

import com.robertx22.enumclasses.Elements;
import com.robertx22.stats.Stat;

public class CriticalDamage extends Stat {
	public CriticalDamage() {
	}

	@Override
	public String Name() {
		return "Critical Damage";
	}

	@Override
	public boolean ScalesToLevel() {
		return false;
	}

	@Override
	public Elements Element() {
		return null;
	}

	@Override
	public boolean IsPercent() {
		return true;
	}

}
