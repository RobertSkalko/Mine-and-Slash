package com.robertx22.database.stats.types.offense.bonus;

import com.robertx22.uncommon.enumclasses.Elements;

public class BonusThunderDamage extends BaseBonusDamage {
	public static String GUID = "Bonus Thunder DMG";

	public BonusThunderDamage() {

	}

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public Elements Element() {
		return Elements.Thunder;
	}

}