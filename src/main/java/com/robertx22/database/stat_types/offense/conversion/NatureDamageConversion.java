package com.robertx22.database.stat_types.offense.conversion;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class NatureDamageConversion extends BaseSpellToBasicDamage {
	public static String GUID = "Nature DMG Conversion";

	public NatureDamageConversion() {

	}

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public Elements Element() {
		return Elements.Nature;
	}

	@Override
	public Stat StatThatGiveDamage() {
		return new SpellNatureDamage();
	}

}