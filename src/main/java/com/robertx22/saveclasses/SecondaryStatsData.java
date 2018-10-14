package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.robertx22.gearitem.ITooltipList;
import com.robertx22.generation.StatGen;
import com.robertx22.interfaces.IWeighted;
import com.robertx22.saveclasses.abstractclasses.StatGroupData;
import com.robertx22.stats.StatMod;
import com.robertx22.utilityclasses.ListUtils;
import com.robertx22.utilityclasses.RandomUtils;
import com.robertx22.utilityclasses.WeightedUtils;

public class SecondaryStatsData extends StatGroupData implements Serializable, ITooltipList {

	private static final long serialVersionUID = 6149243047165372987L;

	public SecondaryStatsData() {

	}

	@Override
	public boolean IfRerollFully() {
		return this.setRerollFully;
	}

	@Override
	public boolean IfRerollNumbers() {
		return this.setRerollNumbers;
	}

	@Override
	public void RerollFully(GearItemData gear) {

		this.level = gear.level;

		this.Mods = new ArrayList<StatModData>();

		int Stats = RandomUtils.RandomRange(1, 3);

		List<IWeighted> possibleStats = ListUtils.CollectionToList(gear.GetBaseGearType().PossibleSecondaryStats());

		while (Stats > 0) {
			StatMod mod = (StatMod) WeightedUtils.WeightedRandom(possibleStats);
			this.Mods.add(StatModData.NewRandom(mod, level));
			Stats--;

		}

		for (StatMod mod : gear.GetBaseGearType().PossibleSecondaryStats()) {
			StatModData moddata = StatModData.NewRandom(mod, level);
			this.Mods.add(moddata);

		}
	}

	@Override
	public void RerollNumbers(GearItemData gear) {

		for (StatModData data : this.Mods) {
			data.percent = StatGen.GenPercent();
		}

	}

	@Override
	public List<String> GetTooltipString() {

		List<String> list = new ArrayList<String>();

		list.add("Secondary Stats: ");

		for (StatModData data : this.GetAllStats()) {

			list.add(data.GetTooltipString());
		}

		return list;

	}

}
