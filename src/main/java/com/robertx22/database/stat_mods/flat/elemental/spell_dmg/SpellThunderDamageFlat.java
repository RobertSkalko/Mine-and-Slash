package com.robertx22.database.stat_mods.flat.elemental.spell_dmg;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
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
