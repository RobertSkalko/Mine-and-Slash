package com.robertx22.database.stat_mods.percent.spell_ele_dmg;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class SpellWaterDamagePercent extends StatMod {

	public SpellWaterDamagePercent() {
	}

	@Override
	public String GUID() {
		return "WaterDamagePercent";
	}

	@Override
	public float Min() {
		return 3;
	}

	@Override
	public float Max() {
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
