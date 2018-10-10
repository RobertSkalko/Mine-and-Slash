package com.robertx22.gearitem;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.classes.BaseAffix;
import com.robertx22.database.Affixes;
import com.robertx22.enums.SuffixOrPrefix;
import com.robertx22.stats.StatData;
import com.robertx22.stats.StatMod;

public class AffixData implements IStatsContainer {

	public AffixData(String name, List<Integer> percents, SuffixOrPrefix type) {
		super();
		Name = name;
		Percents = percents;
		Type = type;
	}

	public String Name;

	public List<Integer> Percents = new ArrayList<Integer>();

	public SuffixOrPrefix Type;

	
	public BaseAffix GetBase() {
		return Affixes.All.get(Name);
	}
	@Override
	public List<StatData> GetAllStats() {

	BaseAffix base = GetBase();
	
	List<StatData> list = new ArrayList<StatData>();
	
	for (int i = 0; i< base.StatMods().size(); i++) {
		
		StatMod mod = base.StatMods().get(i);			
		
		list.add(new StatData(mod.StatRef(), mod.Type(), Percents.get(i)));
	}
	
		
		return list;

	}

}
