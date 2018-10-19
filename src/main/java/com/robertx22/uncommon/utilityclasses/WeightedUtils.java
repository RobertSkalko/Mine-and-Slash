package com.robertx22.uncommon.utilityclasses;

import java.util.List;

public class WeightedUtils {

	public static IWeighted WeightedRandom(List<IWeighted> lootTable) {

		double value = Total(lootTable) * Math.random();
		double weight = 0;

		for (IWeighted item : lootTable) {
			weight += item.Weight();
			if (value < weight)
				return item;
		}

		return null;

	}

	private static int Total(List<IWeighted> list) {

		int total = 0;

		for (IWeighted w : list) {
			total += w.Weight();
		}
		return total;

	}

}
