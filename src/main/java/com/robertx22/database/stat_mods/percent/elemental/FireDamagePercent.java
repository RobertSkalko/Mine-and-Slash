package com.robertx22.database.stat_mods.percent.elemental;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class FireDamagePercent extends StatMod {

	public FireDamagePercent() {
	}

	@Override
	public String GUID() {
		return "FireDamagePercent";
	}

	@Override
	public int Min() {
		return 2;
	}

	@Override
	public int Max() {
		return 10;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Percent;
	}

	@Override
	public Stat GetBaseStat() {
		return new SpellFireDamage();
	}

}
