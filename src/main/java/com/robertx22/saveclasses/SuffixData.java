package com.robertx22.saveclasses;

import java.util.List;

import com.robertx22.database.lists.Affixes;
import com.robertx22.gearitem.BaseAffix;

public class SuffixData extends AffixData {

	private static final long serialVersionUID = 2388285649664919577L;

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

		gear.ReRollPrefixFully();

	}

	@Override
	public void RerollNumbers(GearItemData gear) {
		gear.ReRollPrefixNumbers();

	}

	@Override
	public BaseAffix BaseClass() {
		return Affixes.All.get(baseAffix);
	}

}
