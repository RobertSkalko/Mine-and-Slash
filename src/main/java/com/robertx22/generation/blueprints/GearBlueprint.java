package com.robertx22.generation.blueprints;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.Sets;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.GearTypeStatsData;
import com.robertx22.saveclasses.gearitem.SetData;
import com.robertx22.saveclasses.gearitem.gear_bases.Set;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class GearBlueprint extends ItemBlueprint {

    public GearBlueprint(int level) {
	super(level);
    }

    public String gearType = "";
    public boolean RandomGearType = true;

    public void SetSpecificType(String type) {

	gearType = type;
	RandomGearType = false;

	try {
	    GearTypes.All.get(type);
	} catch (IndexOutOfBoundsException e) {
	    e.printStackTrace();
	}

    }

    public GearTypeStatsData genGearTypeStats(GearItemData data) {

	if (data.GetBaseGearType().slotTypeStats().size() > 0) {

	    GearTypeStatsData stats = new GearTypeStatsData(data.GetBaseGearType().GUID());
	    stats.RerollFully(data);

	    return stats;

	}

	return null;

    }

    public GearItemSlot GetGearType() {

	if (RandomGearType) {
	    List<IWeighted> slots = ListUtils.CollectionToList(GearTypes.All.values());

	    return (GearItemSlot) RandomUtils.WeightedRandom(slots);

	} else {

	    return GearTypes.All.get(gearType);
	}

    }

    private boolean isCustomSetChance = false;
    private float customSetChance = 0;

    public void SetCustomSetChance(float chance) {
	isCustomSetChance = true;
	customSetChance = chance;
    }

    public SetData GenerateSet() {

	SetData setdata = null;

	boolean has = false;

	if (this.isCustomSetChance) {

	    if (RandomUtils.roll(this.customSetChance)) {

		has = true;
	    }

	} else {
	    if (RandomUtils.roll(Rarities.Items.get(rarity).SetChance())) {
		has = true;
	    }
	}

	if (has) {

	    List<Set> possibleSets = new ArrayList();

	    for (Set set : Sets.All.values()) {
		if (set.CanBePlacedOnItemSlot(this.gearType)) {
		    possibleSets.add(set);
		}
	    }

	    if (possibleSets.size() > 0) {
		Set set = (Set) RandomUtils.WeightedRandom(ListUtils.CollectionToList(possibleSets));

		setdata = new SetData();
		setdata.baseSet = set.GUID();
	    }
	}

	return setdata;
    }

}
