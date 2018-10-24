package com.robertx22.database.stats.types.resources;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class ManaRegen extends Stat {

	public ManaRegen() {
		this.StatMinimum = 1;
	}

	@Override
	public String Name() {
		return "Mana Regen";
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return null;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}
