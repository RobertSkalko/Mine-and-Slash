package com.robertx22.database.prefixes;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.classes.Prefix;
import com.robertx22.database.stats.mods.flat.FireDamageFlat;
import com.robertx22.stats.StatMod;

public class Flaming extends Prefix {

	public Flaming() {
	}

	@Override
	public String Name() {
		return "Flaming";
	}

	@Override
	public List<StatMod> StatMods() {

		List<StatMod> mods = new ArrayList<StatMod>();
		mods.add(new FireDamageFlat());

		return mods;

	}

}
