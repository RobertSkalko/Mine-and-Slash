package com.robertx22.database.prefixes;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.mods.percent.elemental.NatureDamagePercent;
import com.robertx22.gearitem.Prefix;
import com.robertx22.stats.StatMod;

public class Thorny extends Prefix {

	public Thorny() {
	}

	@Override
	public String Name() {
		return "Thorny";
	}

	@Override
	public List<StatMod> StatMods() {
		return Arrays.asList(new NatureDamagePercent());
	}

}
