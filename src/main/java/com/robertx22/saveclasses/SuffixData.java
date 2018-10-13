package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.lists.Suffixes;
import com.robertx22.gearitem.BaseAffix;
import com.robertx22.gearitem.Suffix;
import com.robertx22.generation.StatGen;
import com.robertx22.interfaces.IWeighted;
import com.robertx22.saveclasses.abstractclasses.AffixData;
import com.robertx22.stats.StatMod;
import com.robertx22.utilityclasses.ListUtils;
import com.robertx22.utilityclasses.WeightedUtils;

public class SuffixData extends AffixData implements Serializable {

	private static final long serialVersionUID = 8802998468539898482L;

	public SuffixData() {

	}

	public SuffixData(String affixname, List<Integer> percents) {
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

		List<IWeighted> list = ListUtils.CollectionToList(gear.GetBaseGearType().PossibleSuffixes());
		Suffix suffix = (Suffix) WeightedUtils.WeightedRandom(list);

		baseAffix = suffix.Name();

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
		return Suffixes.All.get(baseAffix);
	}

}
