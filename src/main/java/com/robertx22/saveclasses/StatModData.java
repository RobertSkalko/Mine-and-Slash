package com.robertx22.saveclasses;

import java.io.Serializable;

import com.robertx22.database.lists.StatMods;
import com.robertx22.enums.StatTypes;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;

public class StatModData implements Serializable {

	private static final long serialVersionUID = -274938432076951259L;

	public StatModData(String baseModName, StatTypes type, int percent) {
		this.baseModName = baseModName;
		this.type = type;
		this.percent = percent;
	}

	public StatTypes type;
	public int percent;
	public String baseModName;

	public StatMod GetBaseMod() {
		return StatMods.All.get(baseModName);
	}

	public int GetActualVal(int Level) {

		StatMod mod = GetBaseMod();

		Stat stat = mod.GetBaseStat();

		int val = mod.GetValueByPercent(percent);

		if (stat.ScalesToLevel()) {
			val *= Level;
		}

		return val;

	}

}