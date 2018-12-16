package com.robertx22.generation.blueprints;

import com.robertx22.database.rarities.MapRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.generation.RarityGen;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class MapBlueprint extends ItemBlueprint {

    public MapBlueprint(int level, int worldTier) {
	super(level);
	this.setTier(worldTier);

    }

    private int tier = 0;
    private boolean tierRange = true;
    private int tierVariance = 2;

    private static final int MAX_MAP_TIER = 20;

    public void setTier(int i) {
	tier = i;
    }

    public int getTier() {

	if (tierRange) {
	    int thetier = RandomUtils.RandomRange(tier - tierVariance, tier + tierVariance);

	    if (thetier < 1) {
		thetier = 1;
	    }
	    if (thetier > MAX_MAP_TIER) {
		thetier = MAX_MAP_TIER;
	    }

	    return thetier;

	} else {
	    return tier;
	}

    }

    @Override
    public int GetRarity() {

	if (RandomRarity) {

	    if (minRarity > -1) {
		MapRarity rar = Rarities.Maps
			.get(RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Maps)).Rank());

		while (rar.Rank() < minRarity) {
		    rar = Rarities.Maps.get(RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Maps)).Rank());
		}
		return rar.Rank();

	    } else {
		return RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Maps)).Rank();
	    }
	} else {
	    return rarity;
	}

    }
}
