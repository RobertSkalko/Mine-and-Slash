package com.robertx22.GearItemClasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.Enums.SuffixOrPrefix;

public class AffixData {

	public AffixData(String name, List<Integer> percents, SuffixOrPrefix type) {
		super();
		Name = name;
		Percents = percents;
		Type = type;
	}

	public String Name;
	
	public List<Integer> Percents = new ArrayList<Integer>();
	
	public SuffixOrPrefix Type;
	
	
	
	
}
