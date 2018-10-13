package com.robertx22.utilityclasses;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.robertx22.interfaces.IWeighted;

public class WeightedUtils {

	public static IWeighted WeightedRandom(List<IWeighted> lootTable) {
		HashMap<IWeighted, Integer> hashmap = new HashMap<IWeighted, Integer>();

		int total = 0;

		for (IWeighted entry : lootTable) {
			total += entry.Weight();
			hashmap.put(entry, total);
		}

		int result = RandomUtils.RandomRange(0, total);

		for (Entry<IWeighted, Integer> item : hashmap.entrySet()) {
			if (result < item.getValue()) {
				return item.getKey();
			}
		}
		return null;

	}
}
