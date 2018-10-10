package com.robertx22.GearItemClasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.Enums.SuffixOrPrefix;
import com.robertx22.Stats.StatMod;

public abstract class BaseAffix {

	
	public abstract String Name();
	
	public abstract List<StatMod> StatMods();
	
	public abstract SuffixOrPrefix Type();
	
	
}
