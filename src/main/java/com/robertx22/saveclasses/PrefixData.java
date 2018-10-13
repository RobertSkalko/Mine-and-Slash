package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.lists.Prefixes;
import com.robertx22.gearitem.BaseAffix;
import com.robertx22.gearitem.ITooltipList;
import com.robertx22.gearitem.Prefix;
import com.robertx22.generation.StatGen;
import com.robertx22.interfaces.IWeighted;
import com.robertx22.saveclasses.abstractclasses.AffixData;
import com.robertx22.stats.StatMod;
import com.robertx22.utilityclasses.ListUtils;
import com.robertx22.utilityclasses.WeightedUtils;

public class PrefixData extends AffixData implements Serializable, ITooltipList {

	private static final long serialVersionUID = -110285627065158395L;

	public PrefixData() {

	}

	public PrefixData(String affixname, List<Integer> percents) {
		super();
		this.baseAffix = affixname;
		this.percents = percents;
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

		List<IWeighted> list = ListUtils.CollectionToList(gear.GetBaseGearType().PossiblePrefixes());
		Prefix prefix = (Prefix) WeightedUtils.WeightedRandom(list);

		baseAffix = prefix.Name();

		RerollNumbers(gear);

	}

	@Override
	public void RerollNumbers(GearItemData gear) {

		percents = new ArrayList<Integer>();

		for (StatMod mod : BaseAffix().StatMods()) {
			percents.add(StatGen.GenPercent());
		}

	}

	@Override
	public BaseAffix BaseAffix() {
		return Prefixes.All.get(baseAffix);
	}

	@Override
	public List<String> GetTooltipString() {

		BaseAffix affix = BaseAffix();

		List<String> list = new ArrayList<String>();

		list.add("Prefix: " + affix.Name());

		for (StatModData data : this.GetAllStats()) {

			list.add(data.GetTooltipString());
		}

		return list;

	}

}
