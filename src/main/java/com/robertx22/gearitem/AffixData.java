package com.robertx22.gearitem;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.Enums.SuffixOrPrefix;
import com.robertx22.stats.StatData;

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

	@Override
	public List<StatData> GetAllStats() {
		
		
		
		
		return null;

		
	}
	
	
	
	
	
}
