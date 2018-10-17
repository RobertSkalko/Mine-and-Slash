package com.robertx22.generation;

import java.util.List;

import com.robertx22.classes.IWeighted;
import com.robertx22.database.lists.GearTypes;
import com.robertx22.database.lists.Rarities;
import com.robertx22.gearitem.GearItemSlot;
import com.robertx22.gearitem.ItemRarity;
import com.robertx22.utilityclasses.ListUtils;
import com.robertx22.utilityclasses.RandomUtils;
import com.robertx22.utilityclasses.WeightedUtils;

public class GearGenSchema {

	public GearGenSchema(int level) {
		this.level = level;
	}

	public int MagicFind = 0;

	public int rarity;
	public boolean RandomRarity = true;

	public String gearType;
	public boolean RandomGearType = true;

	public int level;
	public boolean LevelRange = true;
	public int LevelVariance = 5;

	public void SetSpecificRarity(int i) {

		rarity = i;
		RandomRarity = false;

		try {
			Rarities.Items.get(i);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	public void SetSpecificType(String i) {

		gearType = i;
		RandomGearType = false;

		try {
			GearTypes.All.get(i);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	public ItemRarity GetRarity() {

		if (RandomRarity) {
			return Rarities.Items.get(RarityGen.Random(0).Rank());

		} else {
			return Rarities.Items.get(rarity);
		}

	}

	public GearItemSlot GetGearType() {

		if (RandomGearType) {
			List<IWeighted> slots = ListUtils.CollectionToList(GearTypes.All.values());

			return (GearItemSlot) WeightedUtils.WeightedRandom(slots);

		} else {

			return GearTypes.All.get(gearType);
		}

	}

	public int GetLevel() {

		if (LevelRange) {
			int lvl = RandomUtils.RandomRange(level - LevelVariance, level + LevelVariance);

			if (lvl < 1) {
				lvl = 1;
			}

			return lvl;

		} else {
			return level;
		}

	}
}
