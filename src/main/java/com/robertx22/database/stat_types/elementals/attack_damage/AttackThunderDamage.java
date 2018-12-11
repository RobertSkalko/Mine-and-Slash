package com.robertx22.database.stat_types.elementals.attack_damage;

import com.robertx22.uncommon.enumclasses.Elements;

public class AttackThunderDamage extends BaseElementalAttackDamage {
    public static String GUID = "Attack Thunder Damage";

    public AttackThunderDamage() {
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public Elements Element() {
	return Elements.Thunder;
    }

    @Override
    public String unlocString() {
	return "attack_thunder_damage";
    }
}
