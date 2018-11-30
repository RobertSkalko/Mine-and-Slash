package com.robertx22.database.stat_mods.flat.elemental.bonus;

import com.robertx22.database.stat_types.elementals.spell_to_attack.NatureSpellToAttackDMG;
import com.robertx22.stats.Stat;

public class NatureSpellToAttackFlat extends BaseSpellToAttackFlat {

	public NatureSpellToAttackFlat() {

	}

	@Override
	public Stat GetBaseStat() {
		return new NatureSpellToAttackDMG();
	}

	@Override
	public String GUID() {
		return "BonusNatureDamageFlat";
	}

}
