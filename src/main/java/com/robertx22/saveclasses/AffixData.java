package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.lists.Affixes;
import com.robertx22.gearitem.BaseAffix;
import com.robertx22.gearitem.IStatsContainer;
import com.robertx22.stats.StatMod;

public class AffixData implements IStatsContainer, Serializable {

	private static final long serialVersionUID = 2388285649664919577L;

	public AffixData() {

	}

	public AffixData(String affixname, List<Integer> percents) {
		super();
		Base = affixname;
		Percents = percents;
	}

	public boolean SetRerollNumbers = false;
	public boolean SetRerollFully = false;
	public List<Integer> Percents = new ArrayList<Integer>();
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
