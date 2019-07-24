package com.robertx22.mine_and_slash.uncommon.testing.tests;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.gens.GearLootGen;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import org.apache.commons.lang3.time.StopWatch;

import java.util.HashMap;
import java.util.Map.Entry;

public class ItemGenRarityTest {

    int amount = 1000;

    public void GenManyItems() {

        StopWatch watch = new StopWatch();
        watch.start();

        HashMap<Integer, Integer> RarityandNumber = new HashMap<Integer, Integer>();

        GearBlueprint schema = new GearBlueprint(1);

        for (int i = 0; i < amount; i++) {

            GearItemData data = Gear.Load(GearLootGen.CreateStack(schema));

            if (RarityandNumber.containsKey(data.Rarity)) {
                RarityandNumber.put(data.Rarity, RarityandNumber.get(data.Rarity) + 1);
            } else {
                RarityandNumber.put(data.Rarity, 1);
            }

        }

        watch.stop();

        for (Entry<Integer, Integer> entry : RarityandNumber.entrySet()) {
            float percent = (float) entry.getValue() / (float) amount * 100;
            System.out.println("Rarity: " + Rarities.Items.get(entry.getKey())
                    .locName() + " has this many items: " + entry.getValue() + " , percent of total: " + Float
                    .toString(percent));
        }
        System.out.println("It took " + watch.getTime() + " miliseconds for " + amount + " items to addPieces, in other words it took this many miliseconds for one item: " + watch
                .getTime() / amount);

    }
}
