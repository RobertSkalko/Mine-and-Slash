package com.robertx22.database.prefixes.offense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.percent.elemental.WaterDamagePercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class Frosty extends Prefix {

	public Frosty() {
	}

	@Override
	public String Name() {
		return "Frosty";
	}

	@Override
	public List<StatMod> StatMods() {

		return Arrays.asList(new WaterDamagePercent());
	}

}
