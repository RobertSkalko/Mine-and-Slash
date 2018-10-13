package com.robertx22.stats;

public abstract class UsableStat extends Stat {

	/**
	 * 0.75 means 75% will be maximum value
	 */
	public abstract float MaximumPercent();

	/**
	 * Used to get usable value. So 5000 armor turns into 50% armor reduction
	 */
	public abstract int AverageStat();

	public int GetUsableValue(int Level, int value) {

		int AvgStat = 50;

		float number = value / (Level * AvgStat);

		int finalval = (int) (MaximumPercent() * number / (number + 25));

		System.out.println("from " + GetActualVal() + "to usable value " + finalval);

		return finalval;

	}
}
