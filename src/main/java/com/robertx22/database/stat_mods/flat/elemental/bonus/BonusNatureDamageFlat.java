package com.robertx22.database.stat_mods.flat.elemental.bonus;

import com.robertx22.database.stat_types.offense.spell_to_attack.NatureSpellToAttackDMG;
import com.robertx22.stats.Stat;

public class BonusNatureDamageFlat extends BaseBonusDamageFlat {

	public BonusNatureDamageFlat() {

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
