package com.robertx22.saveclasses.gearitem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.robertx22.saveclasses.gearitem.gear_bases.BaseAffix;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.stats.StatMod;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public abstract class AffixData implements IStatsContainer, IRerollable {

    @Store
    public List<Integer> percents = new ArrayList<Integer>();

    @Store
    public String baseAffix;

    public abstract BaseAffix BaseAffix();

    @Override
    public List<LevelAndStats> GetAllStats(int level) {

	BaseAffix base = BaseAffix();

	List<StatModData> list = new ArrayList<StatModData>();

	for (int i = 0; i < base.StatMods().size(); i++) {

	    StatMod mod = base.StatMods().get(i);

	    list.add(StatModData.Load(mod, percents.get(i)));
	}

	return Arrays.asList(new LevelAndStats(list, level));

    }

}
