package com.robertx22.saveclasses;

import com.robertx22.database.stat_types.UnknownStat;
import com.robertx22.db_lists.Stats;
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
	if (Stats.All.containsKey(Name)) {
	    return Stats.All.get(Name);
	}
	/*
	 * System.out.println(
	 * "No such stat, this is probably a legacy item and the stat has been renamed or removed: "
	 * + Name);
	 * 
	 */
	return Stats.All.get(UnknownStat.GUID);
    }

    @Store
    public String Name;

    public float Flat = 0;

    public float Percent = 0;

    public float Multi = 0;

    @Store
    public float Value = 0;

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

}
