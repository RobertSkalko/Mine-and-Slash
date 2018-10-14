package com.robertx22.stats;

import com.robertx22.saveclasses.Unit;

public abstract class UsableStat extends Stat {

	/**
	 * 0.75 means 75% will be maximum value
	 */
	public abstract float MaximumPercent();

	/**
	 * Used to get usable value. So 5000 armor turns into 50% armor reduction
	 */
	public abstract int AverageStat();

	public float GetUsableValue(Unit Source, int Level, int value) {

		int AvgStat = 50;

		float number = value / (Level * AvgStat);

		float finalval = (MaximumPercent() * number / (number + 25));

		System.out.println("from " + this.GetValue(Source) + "to usable value " + finalval);

		return finalval;

	}
}
