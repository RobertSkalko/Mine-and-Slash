package com.robertx22.saveclasses;

import com.robertx22.database.lists.Stats;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class StatData {

	public StatData() {

	}

	public StatData(Stat stat) {
		this.Name = stat.GUID();
	}

	public Stat GetStat() {
		return Stats.All.get(Name);
	}

	public String Name;

	public int BaseFlat = 0;

	public float Flat = 0;
	public float Percent = 0;
	public float Multi = 0;

	public float Value;
	public float CurrentValue;

	public void Clear() {
		Flat = 0;
		Percent = 0;
		Multi = 0;
	}

	public void Add(StatModData mod, int level) {

		if (mod.type == StatTypes.Flat) {
			Flat += mod.GetActualVal(level);
		} else if (mod.type == StatTypes.Percent) {
			Percent += mod.GetActualVal(level);
		} else if (mod.type == StatTypes.Multi) {
			Multi += mod.GetActualVal(level);
		}
	}

	public void Increase(int i) {
		CurrentValue += i;
		if (CurrentValue > Value) {
			CurrentValue = (int) Value;
		}
	}

	public void Decrease(int i) {
		CurrentValue -= i;
		if (CurrentValue < 0) {
			CurrentValue = 0;
		}
	}
}
