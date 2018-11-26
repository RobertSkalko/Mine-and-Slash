package com.robertx22.database.stats.types.offense.conversion;

import com.robertx22.database.stats.types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class ThunderDamageConversion extends BaseSpellToBasicDamage {
	public static String GUID = "Thunder DMG Conversion";

	public ThunderDamageConversion() {

	}

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public Elements Element() {
		return Elements.Thunder;
	}

	@Override
	public Stat StatThatGiveDamage() {
		return new AttackThunderDamage();
	}
}