package com.robertx22.saveclasses.abstractclasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.crafting.IRerollable;
import com.robertx22.gearitem.BaseAffix;
import com.robertx22.gearitem.IStatsContainer;
import com.robertx22.saveclasses.StatModData;
import com.robertx22.stats.StatMod;

public abstract class AffixData implements IStatsContainer, IRerollable {

	public boolean setRerollNumbers = false;
	public boolean setRerollFully = false;
	public List<Integer> percents = new ArrayList<Integer>();
	public String baseAffix;

	public abstract BaseAffix BaseAffix();

	@Override
	public List<StatModData> GetAllStats() {

		BaseAffix base = BaseAffix();

		List<StatModData> list = new ArrayList<StatModData>();

		for (int i = 0; i < base.StatMods().size(); i++) {

			StatMod mod = base.StatMods().get(i);

			list.add(new StatModData(mod.GUID(), mod.Type(), percents.get(i)));
		}

		return list;

	}

}
