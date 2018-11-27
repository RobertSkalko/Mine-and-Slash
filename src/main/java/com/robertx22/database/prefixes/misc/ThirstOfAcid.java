package com.robertx22.database.prefixes.misc;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.NatureDamagePercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class ThirstOfAcid extends Prefix {

	@Override
	public String Name() {
		return "Thirst Of Acid";
	}

	@Override
	public List<StatMod> StatMods() {
		return Arrays.asList(new LifestealFlat(), new NatureDamagePercent());
	}

	@Override
	public int Weight() {
		return this.RareWeight;
	}
}
