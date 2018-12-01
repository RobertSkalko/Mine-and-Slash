package com.robertx22.database.stat_types.elementals.spell_damage;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class SpellWaterDamage extends Stat {
	public static String GUID = "Spell Water Damage";

	public SpellWaterDamage() {
	}

	@Override
	public String Guid() {
		return GUID;
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return Elements.Water;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}
