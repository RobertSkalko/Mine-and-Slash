package com.robertx22.database.stats.types;

import com.robertx22.enums.Elements;
import com.robertx22.stats.RangeStatComp;

public class MinDamage extends RangeStatComp {

	public MinDamage() {
		this.StatMinimum = 1;
	}

	@Override
	public String Name() {
		return "ERROR";
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
