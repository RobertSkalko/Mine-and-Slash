package com.robertx22.saveclasses.abstractclasses;

import java.util.List;

import com.robertx22.crafting.IRerollable;
import com.robertx22.gearitem.IStatsContainer;
import com.robertx22.saveclasses.StatModData;

public abstract class StatGroupData implements IStatsContainer, IRerollable {

	public StatGroupData() {

	}

	public List<StatModData> Mods;

	@Override
	public List<StatModData> GetAllStats() {
		return Mods;
	}

	public int level;

	public boolean setRerollNumbers = false;
	public boolean setRerollFully = false;

}
