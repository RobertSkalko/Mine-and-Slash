package com.robertx22.database.stats.types.offense.bonus;

import com.robertx22.uncommon.enumclasses.Elements;

public class BonusFireDamage extends BaseBonusDamage {
	public static String GUID = "Bonus Fire DMG";

	public BonusFireDamage() {

	}

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public Elements Element() {
		return Elements.Fire;
	}

}