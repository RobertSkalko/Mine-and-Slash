package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.robertx22.gearitem.ITooltipList;
import com.robertx22.generation.StatGen;
import com.robertx22.saveclasses.abstractclasses.StatGroupData;
import com.robertx22.stats.StatMod;

public class PrimaryStatsData extends StatGroupData implements Serializable, ITooltipList {

	private static final long serialVersionUID = 1632623308971840392L;

	public PrimaryStatsData() {

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

		for (StatMod mod : gear.GetBaseGearType().PrimaryStats()) {

			StatModData moddata = StatModData.NewRandom(mod, gear.level);

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

		list.add("Primary Stats: ");

		for (StatModData data : this.GetAllStats()) {

			list.add(data.GetTooltipString());
		}

		return list;

	}

}
