package com.robertx22.database.stat_mods.flat.elemental.spell_dmg;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
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
