package com.robertx22.saveclasses.gearitem;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.generation.StatGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueItem;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class UniqueStatsData implements ITooltipList, IRerollable, IStatsContainer {

	public UniqueStatsData() {

	}

	@Store
	public String uniqueGUID;
	@Store
	public List<Integer> percents = new ArrayList<Integer>();

	public UniqueStatsData(String GUID) {
		this.uniqueGUID = GUID;
	}

	@Override
	public void RerollFully(GearItemData gear) {
		this.RerollNumbers(gear);
	}

	@Override
	public void RerollNumbers(GearItemData gear) {

		percents.clear();

		// wont ever have more than 10 unique stats.
		for (int i = 0; i < 10; i++) {
			percents.add(StatGen.GenPercent(gear.GetRarity()));
		}

	}

	@Override
	public List<String> GetTooltipString(GearItemData gear) {

		List<String> list = new ArrayList<String>();

		list.add("Unique Stats: ");

		for (StatModData data : this.GetAllStats(gear.level)) {

			list.add(data.GetTooltipString(gear.GetRarity().StatPercents(), gear.level, true));
		}

		return list;

	}

	public BaseUniqueItem getUniqueItem() {
		return (BaseUniqueItem) BaseUniqueItem.ITEMS.get(this.uniqueGUID);
	}

	@Override
	public List<StatModData> GetAllStats(int level) {

		BaseUniqueItem unique = getUniqueItem();

		List<StatModData> list = new ArrayList<StatModData>();

		for (int i = 0; i < unique.uniqueStats().size(); i++) {
			StatMod mod = unique.uniqueStats().get(i);
			list.add(StatModData.Load(mod, percents.get(i)));
		}

		return list;
	}

}
