package com.robertx22.saveclasses.abstractclasses;

import java.util.List;

import com.robertx22.crafting.IRerollable;
import com.robertx22.gearitem.IStatsContainer;
import com.robertx22.saveclasses.StatModData;

public abstract class StatGroupData implements IStatsContainer, IRerollable {

	public StatGroupData(List<StatModData> Mods) {
		this.Mods = Mods;
	}

	public List<StatModData> Mods;

	@Override
	public List<StatModData> GetAllStats() {
		return Mods;
	}

}
