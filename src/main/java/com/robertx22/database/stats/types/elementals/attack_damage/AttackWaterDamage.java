package com.robertx22.database.stats.types.elementals.attack_damage;

import com.robertx22.uncommon.enumclasses.Elements;

public class AttackWaterDamage extends BaseElementalAttackDamage {
	public static String GUID = "Attack Water Damage";

	public AttackWaterDamage() {
	}

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public Elements Element() {
		return Elements.Water;
	}

}
