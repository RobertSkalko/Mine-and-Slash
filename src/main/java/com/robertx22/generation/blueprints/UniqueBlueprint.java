package com.robertx22.generation.blueprints;

import java.util.List;

import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import com.robertx22.unique_items.IUnique;

public class UniqueBlueprint extends GearBlueprint {

    public UniqueBlueprint(int level, int map_tier, boolean randomTier) {
	super(level);
	this.randomTier = randomTier;
	this.map_tier = map_tier;
    }

    private int map_tier = 0;
    private int tier = -1;

    private boolean randomTier = true;

    public int GetTier() {

	if (tier < 0) {

	    if (randomTier) {

		if (map_tier == 0) {
		    tier = 0;
		} else {
		    tier = RandomUtils.RandomRange(0, this.map_tier);
		}

	    } else {

		tier = map_tier;
	    }
	}

	return tier;

    }

    public IUnique getUnique() {

	tier = this.GetTier();

	if (this.randomTier == false) {
	    return (IUnique) RandomUtils.WeightedRandom(
		    ListUtils.CollectionToList(IUnique.getAllUniquesOfTier(map_tier, IUnique.ITEMS.values())));
	} else {
	    return randomUnique();
	}

    }

    private IUnique randomUnique() {

	List<IUnique> possible = IUnique.filterUniquesByType(gearType,
		IUnique.getAllPossibleUniqueDrops(map_tier, IUnique.ITEMS.values()));

	IUnique unique = (IUnique) RandomUtils.WeightedRandom(ListUtils.CollectionToList(possible));

	return unique;

    }

}
