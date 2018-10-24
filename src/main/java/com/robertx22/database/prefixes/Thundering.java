package com.robertx22.database.prefixes;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.mods.percent.elemental.ThunderDamagePercent;
import com.robertx22.gearitem.Prefix;
import com.robertx22.stats.StatMod;

public class Thundering extends Prefix {

	public Thundering() {
	}

	@Override
	public String Name() {
		return "Thundering";
	}

	@Override
	public List<StatMod> StatMods() {
		return Arrays.asList(new ThunderDamagePercent());
	}

}
