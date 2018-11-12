package com.robertx22.stats;

import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.enumclasses.Elements;

public abstract class Trait extends Stat {

	public abstract String Description();

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
		return false;
	}

	@Override
	public int CalcVal(StatData data, Unit Source) {

		if (data.Flat > 0) {
			data.Value = 1;

			return 1;

		} else {
			data.Value = 0;
			return 0;
		}

	}
}
