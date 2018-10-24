package com.robertx22.stats;

import com.robertx22.saveclasses.Unit;

public abstract class Trait extends Stat implements IStatEffects {

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
