package com.robertx22.stats;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.enumclasses.Elements;

public abstract class Trait extends Stat {

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
	public int CalcVal(Unit Source) {

		if (this.Flat > 0) {
			this.Value = 1;

			return 1;

		} else {
			this.Value = 0;
			return 0;
		}

	}
}
