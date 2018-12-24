package com.robertx22.saveclasses.gearitem;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public abstract class StatGroupData implements IStatsContainer {

    public StatGroupData() {

    }

    @Store
    public List<StatModData> Mods = new ArrayList<StatModData>();

    @Override
    public List<StatModData> GetAllStats(int level) {
	return Mods;
    }

}
