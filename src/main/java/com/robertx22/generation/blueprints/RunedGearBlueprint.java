package com.robertx22.generation.blueprints;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.generation.RarityGen;
import com.robertx22.uncommon.utilityclasses.ListUtils;

public class RunedGearBlueprint extends GearBlueprint {

    public RunedGearBlueprint(int level) {
	super(level);

    }

    @Override
    public int GetRarity() {

	if (RandomRarity) {

	    if (minRarity > -1) {
		ItemRarity rar = Rarities.RunedItems
			.get(RarityGen.Random(0, ListUtils.CollectionToList(Rarities.RunedItems)).Rank());

		while (rar.Rank() < minRarity) {
		    rar = Rarities.RunedItems
			    .get(RarityGen.Random(0, ListUtils.CollectionToList(Rarities.RunedItems)).Rank());
		}
		return rar.Rank();

	    } else {
		return RarityGen.Random(0, ListUtils.CollectionToList(Rarities.RunedItems)).Rank();
	    }
	} else {
	    return rarity;
	}

    }
}
