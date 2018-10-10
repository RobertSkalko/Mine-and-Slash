package com.robertx22.gearitem;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.Enums.SuffixOrPrefix;
import com.robertx22.stats.StatMod;

public abstract class BaseAffix {

	
	public abstract String Name();
	
	public abstract List<StatMod> StatMods();
	
	public abstract SuffixOrPrefix Type();
	
	
}
