package com.robertx22.stats;

public abstract class UsableStat extends Stat {

	public UsableStat() {

	}

	/**
	 * 0.75 means 75% will be maximum value
	 */
	public abstract float MaximumPercent();

	/**
	 * Used to get usable value. So 5000 armor turns into 50% armor reduction
	 */
	public abstract int AverageStat();

	public float GetUsableValue(int Level, int value) {

		int AvgStat = 10;

		float number = (float) value / ((float) Level * (float) AvgStat);

		float finalval = (float) (MaximumPercent() * (float) number / ((float) number + (float) 10));

		// System.out.println("from " + this.Value + "to usable value " + finalval);

		return finalval;

	}
}
