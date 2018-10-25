package com.robertx22.saveclasses.abstractclasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.gearitem.IStatsContainer;
import com.robertx22.saveclasses.gearitem.GearItemData;
import com.robertx22.saveclasses.gearitem.StatModData;

public abstract class StatGroupData implements IStatsContainer {

	public StatGroupData() {

	}

	public List<StatModData> Mods = new ArrayList<StatModData>();

	@Override
	public List<StatModData> GetAllStats(GearItemData gear) {
		return Mods;
	}

	public boolean setRerollNumbers = false;
	public boolean setRerollFully = false;

}
