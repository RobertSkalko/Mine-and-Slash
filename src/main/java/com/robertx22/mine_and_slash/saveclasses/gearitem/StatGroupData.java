package com.robertx22.mine_and_slash.saveclasses.gearitem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public abstract class StatGroupData implements IStatsContainer {

    public StatGroupData() {

    }

    @Store
    public List<StatModData> Mods = new ArrayList<StatModData>();

    @Override
    public List<LevelAndStats> GetAllStats(int level) {
	return Arrays.asList(new LevelAndStats(Mods, level));
    }

}
