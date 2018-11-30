package com.robertx22.database.stat_mods.flat.elemental.bonus;

import com.robertx22.database.stat_types.elementals.spell_to_attack.ThunderSpellToAttackDMG;
import com.robertx22.stats.Stat;

public class ThunderSpellToAttackFlat extends BaseSpellToAttackFlat {

	public ThunderSpellToAttackFlat() {

	}

	@Override
	public Stat GetBaseStat() {
		return new ThunderSpellToAttackDMG();
	}

	@Override
	public String GUID() {
		return "BonusThunderDamageFlat";
	}

}
