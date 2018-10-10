package com.robertx22.database.stats.Suffixes;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.Enums.SuffixOrPrefix;
import com.robertx22.gearitem.BaseAffix;
import com.robertx22.stats.StatMod;

public class Flaming extends BaseAffix {

	@Override
	public String Name() {
		
		return "Flaming";
	}

	@Override
	public List<StatMod> StatMods() {
		 List<StatMod> mods = new ArrayList<StatMod>();
	
		// mods.add(new StatMod());
		 
		 return mods;
	}

	@Override
	public SuffixOrPrefix Type() {	
		return SuffixOrPrefix.Prefix;
	}

}
