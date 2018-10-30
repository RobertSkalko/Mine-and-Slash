package com.robertx22.uncommon.testing.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.time.StopWatch;

import com.robertx22.database.lists.Stats;
import com.robertx22.generation.GearGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.stats.Stat;
import com.robertx22.stats.Trait;
import com.robertx22.uncommon.datasaving.GearSaving;
import com.robertx22.uncommon.testing.TestUnit;

public class MaximumPossibleStat {

	int amount = 9500;

	List<GearItemData> items;

	public void CalculateByMassRandomization() {

		System.out.println(" ------- Commencing Near Max possible stat calculations");

		StopWatch watch = new StopWatch();
		watch.start();

		GearBlueprint schema = new GearBlueprint(1);
		schema.rarity = 5; // max possible

		items = new ArrayList<GearItemData>();

		for (int i = 0; i < amount; i++) {
			items.add(GearSaving.Load(GearGen.Create(schema)));

		}

		for (Stat stat : Stats.All.values()) {

			if (stat instanceof Trait) {
				continue;
			}

			HashMap<String, GearItemData> TypeAndGear = new HashMap<String, GearItemData>();

			for (GearItemData item : items) {

				if (!TypeAndGear.containsKey(item.gearTypeName)) {
					TypeAndGear.put(item.gearTypeName, item);
				} else {

					HashMap<String, GearItemData> test = new HashMap<String, GearItemData>();
					test.put(item.gearTypeName, item);

					int currentTotal = new TestUnit()
							.GetStatFromTestGears(new ArrayList<GearItemData>(TypeAndGear.values()), stat);

					int newTotal = new TestUnit().GetStatFromTestGears(new ArrayList<GearItemData>(test.values()),
							stat);

					if (newTotal > currentTotal) {
						TypeAndGear.put(item.gearTypeName, item);
					}
				}
			}

			int maxTotal = new TestUnit().GetStatFromTestGears(new ArrayList<GearItemData>(TypeAndGear.values()), stat);

			System.out.println(stat.Name() + ": " + maxTotal);

		}

		watch.stop();

		System.out.println(" ------- This took " + watch.getTime() + " miliseconds");

	}

}
