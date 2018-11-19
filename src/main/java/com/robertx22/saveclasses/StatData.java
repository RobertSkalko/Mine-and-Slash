package com.robertx22.saveclasses;

import com.robertx22.database.lists.Stats;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.StatTypes;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class StatData {

	public StatData() {

	}

	public StatData(Stat stat) {
		this.Name = stat.GUID();
	}

	public Stat GetStat() {
		return Stats.All.get(Name);
	}

	@Store
	public String Name;

	@Store
	public int BaseFlat = 0;

	@Store
	public float Flat = 0;

	@Store
	public float Percent = 0;

	@Store
	public float Multi = 0;

	@Store
	public float Value;

	@Store
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
