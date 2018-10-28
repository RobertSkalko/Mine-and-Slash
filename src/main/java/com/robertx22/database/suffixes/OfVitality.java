package com.robertx22.database.suffixes;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.mods.flat.HealthFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
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

		return Arrays.asList(new HealthFlat());

	}

}
