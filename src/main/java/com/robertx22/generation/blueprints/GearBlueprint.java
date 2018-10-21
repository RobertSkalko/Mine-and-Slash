package com.robertx22.generation.blueprints;

import java.util.List;

import com.robertx22.database.lists.GearTypes;
import com.robertx22.gearitem.GearItemSlot;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class GearBlueprint extends ItemBlueprint {

	public GearBlueprint(int level) {
		super(level);
	}

	public String gearType;
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

	public GearItemSlot GetGearType() {

		if (RandomGearType) {
			List<IWeighted> slots = ListUtils.CollectionToList(GearTypes.All.values());

			return (GearItemSlot) RandomUtils.WeightedRandom(slots);

		} else {

			return GearTypes.All.get(gearType);
		}

	}

}
