package com.robertx22.database.prefixes.offense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.mods.flat.elemental.bonus.BonusFireDamageFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class FlameImbued extends Prefix {

	public FlameImbued() {
	}

	@Override
	public String Name() {
		return "Flame Imbued";
	}

	@Override
	public List<StatMod> StatMods() {

		return Arrays.asList(new BonusFireDamageFlat());
	}

}
