package com.robertx22.saveclasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.lists.Affixes;
import com.robertx22.enums.SuffixOrPrefix;
import com.robertx22.gearitem.BaseAffix;
import com.robertx22.gearitem.IStatsContainer;
import com.robertx22.stats.StatMod;

public class AffixData implements IStatsContainer {

	public AffixData() {

	}

	public AffixData(String affixname, List<Integer> percents, SuffixOrPrefix type) {
		super();
		Base = affixname;
		Percents = percents;
		Type = type;
	}

	public List<Integer> Percents = new ArrayList<Integer>();

	public SuffixOrPrefix Type;

	public String Base;

	public BaseAffix BaseClass() {

		return Affixes.All.get(Base);
	}

	@Override
	public List<StatModData> GetAllStats() {

		BaseAffix base = BaseClass();

		List<StatModData> list = new ArrayList<StatModData>();

		for (int i = 0; i < base.StatMods().size(); i++) {

			StatMod mod = base.StatMods().get(i);

			list.add(new StatModData(mod.GUID(), mod.Type(), Percents.get(i)));
		}

		return list;

	}

}
