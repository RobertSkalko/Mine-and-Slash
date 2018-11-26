package com.robertx22.database.stats.mods.flat.elemental.spell_dmg;

import com.robertx22.database.stats.types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.stats.Stat;

public class SpellThunderDamageFlat extends BaseEleSpellDmgFlat {

	public SpellThunderDamageFlat() {
	}

	@Override
	public String GUID() {
		return "SpellThunderDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new SpellThunderDamage();
	}

}
