package com.robertx22.database.stat_mods.flat.elemental.bonus;

import com.robertx22.database.stat_types.offense.spell_to_attack.ThunderSpellToAttackDMG;
import com.robertx22.stats.Stat;

public class BonusThunderDamageFlat extends BaseBonusDamageFlat {

	public BonusThunderDamageFlat() {

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
