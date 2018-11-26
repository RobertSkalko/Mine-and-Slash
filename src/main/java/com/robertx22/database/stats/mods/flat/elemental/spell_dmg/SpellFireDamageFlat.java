package com.robertx22.database.stats.mods.flat.elemental.spell_dmg;

import com.robertx22.database.stats.types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.stats.Stat;

public class SpellFireDamageFlat extends BaseEleSpellDmgFlat {

	public SpellFireDamageFlat() {
	}

	@Override
	public String GUID() {
		return "SpellFireDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new SpellFireDamage();
	}

}
