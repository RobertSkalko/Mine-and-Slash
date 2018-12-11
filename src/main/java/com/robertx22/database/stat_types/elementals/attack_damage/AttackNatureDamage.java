package com.robertx22.database.stat_types.elementals.attack_damage;

import com.robertx22.uncommon.enumclasses.Elements;

public class AttackNatureDamage extends BaseElementalAttackDamage {
    public static String GUID = "Attack Nature Damage";

    public AttackNatureDamage() {
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public Elements Element() {
	return Elements.Nature;
    }

    @Override
    public String unlocString() {
	return "attack_nature_damage";
    }
}
