package com.robertx22.database.prefixes.offense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.mods.percent.DamagePercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class Tough extends Prefix {

	public Tough() {
	}

	@Override
	public String Name() {
		return "Tough";
	}

	@Override
	public List<StatMod> StatMods() {
		return Arrays.asList(new DamagePercent());
	}

}
