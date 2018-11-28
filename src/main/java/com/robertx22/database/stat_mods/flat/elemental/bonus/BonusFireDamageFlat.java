package com.robertx22.database.stat_mods.flat.elemental.bonus;

import com.robertx22.database.stat_types.elementals.spell_to_attack.FireSpellToAttackDMG;
import com.robertx22.stats.Stat;

public class BonusFireDamageFlat extends BaseBonusDamageFlat {

	public BonusFireDamageFlat() {

	}

	@Override
	public Stat GetBaseStat() {
		return new FireSpellToAttackDMG();
	}

	@Override
	public String GUID() {
		return "BonusFireDamageFlat";
	}

}
