package com.robertx22.database.suffixes;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.mods.percent.ArmorPercent;
import com.robertx22.gearitem.Suffix;
import com.robertx22.stats.StatMod;

public class OfRockSkin extends Suffix {

	public OfRockSkin() {
	}

	@Override
	public String Name() {
		return "Of Rock Skin";
	}

	@Override
	public List<StatMod> StatMods() {

		return Arrays.asList(new ArmorPercent());

	}

}
