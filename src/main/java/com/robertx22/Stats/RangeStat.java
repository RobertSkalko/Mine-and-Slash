package com.robertx22.stats;

import com.robertx22.classes.Unit;

public abstract class RangeStat extends Stat {

	public abstract Stat MinStat();

	public abstract Stat MaxStat();

	@Override
	public Double GetValue(Unit Source) {

		// min + max random

		return null;

	}

	@Override
	public String ToTooltipString(Unit Source) {

		return Name() + " " + MinStat().GetValue(Source) + "-" + MaxStat().GetValue(Source);
	}

}
