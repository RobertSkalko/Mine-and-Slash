package com.robertx22.database.prefixes;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.mods.percent.LifestealPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class LifeStealing extends Prefix {

	public LifeStealing() {
	}

	@Override
	public String Name() {
		return "LifeStealing";
	}

	@Override
	public List<StatMod> StatMods() {

		return Arrays.asList(new LifestealPercent());
	}

}
