package com.robertx22.database.suffixes;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.classes.Suffix;
import com.robertx22.database.stats.mods.flat.HealthFlat;
import com.robertx22.stats.StatMod;

public class OfVitality extends Suffix {

	public OfVitality() {
	}

	@Override
	public String Name() {
		return "Of Vitality";
	}

	@Override
	public List<StatMod> StatMods() {

		List<StatMod> mods = new ArrayList<StatMod>();
		mods.add(new HealthFlat());

		return mods;

	}

}
