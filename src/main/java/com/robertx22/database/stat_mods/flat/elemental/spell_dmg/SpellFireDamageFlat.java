package com.robertx22.database.stat_mods.flat.elemental.spell_dmg;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
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
