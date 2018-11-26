package com.robertx22.database.stats.mods.flat.elemental.spell_dmg;

import com.robertx22.database.stats.types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.stats.Stat;

public class SpellNatureDamageFlat extends BaseEleSpellDmgFlat {

	public SpellNatureDamageFlat() {
	}

	@Override
	public String GUID() {
		return "SpellNatureDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new SpellNatureDamage();
	}

}
