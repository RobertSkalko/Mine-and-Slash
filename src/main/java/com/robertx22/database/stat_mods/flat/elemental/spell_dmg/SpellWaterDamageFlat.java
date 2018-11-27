package com.robertx22.database.stat_mods.flat.elemental.spell_dmg;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.stats.Stat;

public class SpellWaterDamageFlat extends BaseEleSpellDmgFlat {

	public SpellWaterDamageFlat() {
	}

	@Override
	public String GUID() {
		return "SpellWaterDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new SpellWaterDamage();
	}

}
