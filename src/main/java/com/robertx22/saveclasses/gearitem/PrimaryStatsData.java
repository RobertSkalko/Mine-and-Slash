package com.robertx22.saveclasses.gearitem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.robertx22.generation.StatGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class PrimaryStatsData extends StatGroupData implements Serializable, ITooltipList, IRerollable {

	private static final long serialVersionUID = 1632623308971840392L;

	public PrimaryStatsData() {

	}

	@Override
	public void RerollFully(GearItemData gear) {

		this.Mods = new ArrayList<StatModData>();

		List<IWeighted> possibleStats = ListUtils.CollectionToList(gear.GetBaseGearType().PrimaryStats());

		StatMod mod = (StatMod) RandomUtils.WeightedRandom(possibleStats);

		StatModData moddata = StatModData.NewRandom(gear, mod);

		this.Mods.add(moddata);

	}

	@Override
	public void RerollNumbers(GearItemData gear) {

		for (StatModData data : this.Mods) {
			data.percent = StatGen.GenPercent(gear.GetRarity());
		}

	}

	@Override
	public List<String> GetTooltipString(GearItemData gear) {

		List<String> list = new ArrayList<String>();

		list.add("Primary Stats: ");

		for (StatModData data : this.GetAllStats(gear.level)) {

			list.add(data.GetTooltipString(gear.level, gear, true));
		}

		return list;

	}

}
