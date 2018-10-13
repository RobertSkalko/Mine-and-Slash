package com.robertx22.saveclasses;

import java.io.Serializable;

import com.robertx22.database.lists.StatMods;
import com.robertx22.enums.StatTypes;
import com.robertx22.generation.StatGen;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;

public class StatModData implements Serializable {

	private static final long serialVersionUID = -274938432076951259L;

	public StatModData() {

	}

	public static StatModData NewRandom(StatMod mod) {

		StatModData data = new StatModData();

		data.baseModName = mod.GUID();
		data.type = mod.Type();
		data.percent = StatGen.GenPercent();

		return data;
	}

	public static StatModData Load(StatMod mod, int percent) {

		StatModData data = new StatModData();

		data.baseModName = mod.GUID();
		data.type = mod.Type();
		data.percent = percent;

		return data;
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