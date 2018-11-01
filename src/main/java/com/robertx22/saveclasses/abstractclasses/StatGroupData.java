package com.robertx22.saveclasses.abstractclasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;

public abstract class StatGroupData implements IStatsContainer {

	public StatGroupData() {

	}

	public List<StatModData> Mods = new ArrayList<StatModData>();

	@Override
	public List<StatModData> GetAllStats(int level) {
		return Mods;
	}

}
