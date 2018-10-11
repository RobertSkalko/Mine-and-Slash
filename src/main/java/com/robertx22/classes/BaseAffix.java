package com.robertx22.classes;

import java.util.List;

import com.robertx22.enums.SuffixOrPrefix;
import com.robertx22.stats.StatMod;

public abstract class BaseAffix {
	
	public BaseAffix() {}
	
	public abstract String Name();

	public abstract List<StatMod> StatMods();

	//public abstract SuffixOrPrefix Type();

}
