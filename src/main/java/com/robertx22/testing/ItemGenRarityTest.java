package com.robertx22.testing;

import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

import com.robertx22.database.lists.Rarities;
import com.robertx22.generation.GearGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saving.Saving;

public class ItemGenRarityTest {

	int amount = 10000;

	@Test
	public void GenManyItems() {

		StopWatch watch = new StopWatch();
		watch.start();

		HashMap<Integer, Integer> RarityandNumber = new HashMap<Integer, Integer>();

		for (int i = 0; i < amount; i++) {

			GearItemData data = Saving.Load(GearGen.Random().getTagCompound(), GearItemData.class);

			if (RarityandNumber.containsKey(data.Rarity)) {
				RarityandNumber.put(data.Rarity, RarityandNumber.get(data.Rarity) + 1);
			} else {
				RarityandNumber.put(data.Rarity, 1);
			}

		}

		watch.stop();

		for (Entry<Integer, Integer> entry : RarityandNumber.entrySet()) {
			System.out.println("Rarity: " + Rarities.Items.get(entry.getKey()).Name() + " has this many items: "
					+ entry.getValue());
		}
		System.out.println("It took " + watch.getTime() + " miliseconds for " + amount
				+ " items to generate, in other words it took this many miliseconds for one item: "
				+ watch.getTime() / amount);

	}
}
