package com.robertx22.database.prefixes;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.mods.percent.elemental.WaterDamagePercent;
import com.robertx22.gearitem.Prefix;
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
