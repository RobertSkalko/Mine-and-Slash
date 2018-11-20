package com.robertx22.saveclasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.enumclasses.AffectedEntities;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class MapItemData {

	@Store
	public String name;

	@Store
	public int level;

	@Store
	public int tier;

	@Store
	public int rarity;

	@Store
	public List<MapAffixData> affixes = new ArrayList<MapAffixData>();

	@Store
	public String worldGeneratorName;

	public int getBonusLootAmount() {

		return (int) (getTotalPercents() * 0.4F);

	}

	public int getBonusLootRarity() {

		return (int) (getTotalPercents() * 0.7F);

	}

	private int getTotalPercents() {

		int total = 0;
		for (MapAffixData affix : affixes) {
			total += affix.percent;
		}
		return total;
	}

	public List<MapAffixData> getAllAffixesThatAffect(AffectedEntities affected) {

		return (List<MapAffixData>) affixes.stream().filter(x -> x.affectedEntities.equals(affected));

	}

}
