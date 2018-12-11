package com.robertx22.database.stat_types.elementals.spell_to_attack;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class FireSpellToAttackDMG extends BaseSpellToBasicDamage {
    public static String GUID = "Fire Spell to Attack DMG";

    public FireSpellToAttackDMG() {

    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public Elements Element() {
	return Elements.Fire;
    }

    @Override
    public Stat StatThatGiveDamage() {
	return new SpellFireDamage();
    }

    @Override
    public String unlocString() {
	return "fire_spell_to_attack";
    }
}