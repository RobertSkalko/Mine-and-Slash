package com.robertx22.stats;

import com.robertx22.enums.StatTypes;
import com.robertx22.saving.IHasBaseClass;

public class StatModData implements IHasBaseClass {

	public StatModData(String baseclass, StatTypes type, int percent) {
		this.base = baseclass;
		this.type = type;
		this.percent = percent;
	}

	public StatTypes type;
	public int percent;
	public String base;

	@Override
	public String BaseClass() {

		return base;
	}

	public Stat GetBaseStat() {
		StatMod mod = GetBase();

		Stat stat = mod.GetBase();

		return stat;

	}

	public int GetActualVal(int Level) {

		StatMod mod = GetBase();

		Stat stat = mod.GetBase();

		int val = mod.GetValueByPercent(percent);

		if (stat.ScalesToLevel()) {
			val *= Level;
		}

		return val;

	}

}