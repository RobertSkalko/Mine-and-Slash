package com.robertx22.saveclasses.abstractclasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.BaseAffix;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.stats.StatMod;

public abstract class AffixData implements IStatsContainer, IRerollable {

	public List<Integer> percents = new ArrayList<Integer>();
	public String baseAffix;

	public abstract BaseAffix BaseAffix();

	@Override
	public List<StatModData> GetAllStats(GearItemData gear) {

		BaseAffix base = BaseAffix();

		List<StatModData> list = new ArrayList<StatModData>();

		for (int i = 0; i < base.StatMods().size(); i++) {

			StatMod mod = base.StatMods().get(i);

			list.add(StatModData.Load(mod, percents.get(i), gear.level));
		}

		return list;

	}

}
