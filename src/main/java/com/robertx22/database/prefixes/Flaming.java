package com.robertx22.database.prefixes;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.stats.mods.flat.elemental.FireDamageFlat;
import com.robertx22.gearitem.Prefix;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.IWeighted;

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

	@Override
	public int Weight() {
		return IWeighted.NormalWeight;
	}

}
