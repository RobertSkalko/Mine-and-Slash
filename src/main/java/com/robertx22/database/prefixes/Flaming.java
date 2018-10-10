package com.robertx22.database.prefixes;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.classes.BaseAffix;
import com.robertx22.database.stats.mods.flat.FireDamageFlat;
import com.robertx22.enums.SuffixOrPrefix;
import com.robertx22.stats.StatMod;

public class Flaming extends BaseAffix{

	@Override
	public String Name() {		
		return "Flaming";
	}

	@Override
	public List<StatMod> StatMods() {
		
		List<StatMod> mods = new ArrayList<StatMod>();
		mods.add(new FireDamageFlat());
		
		return null;
		
	}

	@Override
	public SuffixOrPrefix Type() {
		return SuffixOrPrefix.Prefix;
	}

}
