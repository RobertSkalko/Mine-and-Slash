package com.robertx22.database.suffixes;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.stats.mods.percent.ArmorPercent;
import com.robertx22.gearitem.Suffix;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public class OfRockSkin extends Suffix {

	public OfRockSkin() {
	}

	@Override
	public String Name() {
		return "Of Rock Skin";
	}

	@Override
	public List<StatMod> StatMods() {

		List<StatMod> mods = new ArrayList<StatMod>();
		mods.add(new ArmorPercent());

		return mods;

	}

	@Override
	public int Weight() {
		return IWeighted.NormalWeight;
	}

}
