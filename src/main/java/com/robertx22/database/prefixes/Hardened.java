package com.robertx22.database.prefixes;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.mods.percent.ArmorPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class Hardened extends Prefix {

	public Hardened() {
	}

	@Override
	public String Name() {
		return "Hardened";
	}

	@Override
	public List<StatMod> StatMods() {

		return Arrays.asList(new ArmorPercent());

	}

}
