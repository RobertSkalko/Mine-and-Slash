package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.List;

import com.robertx22.gearitem.IStatsContainer;

public class StatGroupData implements Serializable, IStatsContainer {

	public StatGroupData(List<StatModData> Mods) {
		this.Mods = Mods;
	}

	private static final long serialVersionUID = 8792903236028509230L;

	public List<StatModData> Mods;

	@Override
	public List<StatModData> GetAllStats() {
		return Mods;
	}

}
