package com.robertx22.database.stats.types.elementals.all_damage;

import com.robertx22.uncommon.enumclasses.Elements;

public class AllWaterDamage extends AllEleDamageBase {
	public static String GUID = "All Water Damage";

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public Elements Element() {
		return Elements.Water;
	}

}
