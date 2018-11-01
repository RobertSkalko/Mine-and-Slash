package com.robertx22.database.suffixes.defense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.mods.percent.ArmorPercent;
import com.robertx22.database.stats.mods.percent.HealthPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

public class OfImmortality extends Suffix {

	public OfImmortality() {
	}

	@Override
	public String Name() {
		return "Of Immortality";
	}

	@Override
	public List<StatMod> StatMods() {

		return Arrays.asList(new HealthPercent(), new ArmorPercent());

	}

	@Override
	public int Weight() {
		return this.RareWeight;
	}
}