package com.robertx22.database.stats.types.offense.bonus;

import com.robertx22.database.stats.types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.stats.Stat;
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

	@Override
	public Stat StatThatGiveDamage() {
		return new AttackThunderDamage();
	}
}