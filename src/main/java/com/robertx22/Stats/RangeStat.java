package com.robertx22.stats;

import com.robertx22.classes.Unit;

public abstract class RangeStat extends Stat {

	public abstract RangeStatComp MinStat();

	public abstract RangeStatComp MaxStat();

	@Override
	public Double GetValue(Unit Source) {

		// min + max random

		return null;

	}

}
