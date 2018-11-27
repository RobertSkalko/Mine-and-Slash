package com.robertx22.database.stat_mods.percent.elemental;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class WaterDamagePercent extends StatMod {

	public WaterDamagePercent() {
	}

	@Override
	public String GUID() {
		return "WaterDamagePercent";
	}

	@Override
	public int Min() {
		return 3;
	}

	@Override
	public int Max() {
		return 12;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Percent;
	}

	@Override
	public Stat GetBaseStat() {
		return new SpellWaterDamage();
	}

}
