package com.robertx22.generation.blueprints;

import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.rarities.RuneRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.Runes;
import com.robertx22.generation.RarityGen;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class RuneBlueprint extends ItemBlueprint {

    public RuneBlueprint(int level) {
	super(level);

    }

    BaseRuneItem rune = null;

    public BaseRuneItem getRuneItem() {

	if (rune != null) {
	    return rune;
	}

	else {

	    rune = (BaseRuneItem) RandomUtils.WeightedRandom(ListUtils.CollectionToList(Runes.All.values()));

	    return rune;

	}

    }

    @Override
    public int GetRarity() {

	if (RandomRarity) {

	    if (minRarity > -1) {
		RuneRarity rar = Rarities.Runes
			.get(RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Runes)).Rank());

		while (rar.Rank() < minRarity) {
		    rar = Rarities.Runes.get(RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Runes)).Rank());
		}
		return rar.Rank();

	    } else {
		return RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Runes)).Rank();
	    }
	} else {
	    return rarity;
	}

    }

}
