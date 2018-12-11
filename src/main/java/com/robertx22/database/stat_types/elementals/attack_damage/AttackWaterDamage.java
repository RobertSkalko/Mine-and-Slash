package com.robertx22.database.stat_types.elementals.attack_damage;

import com.robertx22.uncommon.enumclasses.Elements;

public class AttackWaterDamage extends BaseElementalAttackDamage {
    public static String GUID = "Attack Water Damage";

    public AttackWaterDamage() {
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public Elements Element() {
	return Elements.Water;
    }

    @Override
    public String unlocString() {
	return "attack_water_damage";
    }
}
