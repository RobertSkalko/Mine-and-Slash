package com.robertx22.database.stats.types.offense.bonus;

import com.robertx22.database.stats.types.elementals.damage.WaterDamage;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class BonusWaterDamage extends BaseBonusDamage {
	public static String GUID = "Bonus Water DMG";

	public BonusWaterDamage() {

	}

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public Elements Element() {
		return Elements.Water;
	}

	@Override
	public Stat StatThatGiveDamage() {
		return new WaterDamage();
	}
}