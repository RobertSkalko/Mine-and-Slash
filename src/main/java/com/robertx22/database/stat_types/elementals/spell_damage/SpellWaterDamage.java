package com.robertx22.database.stat_types.elementals.spell_damage;

import com.robertx22.uncommon.enumclasses.Elements;

public class SpellWaterDamage extends BaseSpellDamage {
    public static String GUID = "Spell Water Damage";

    public SpellWaterDamage() {
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public boolean ScalesToLevel() {
	return true;
    }

    @Override
    public Elements Element() {
	return Elements.Water;
    }

    @Override
    public boolean IsPercent() {
	return false;
    }

    @Override
    public String unlocString() {
	return "spell_water_damage";
    }

}
