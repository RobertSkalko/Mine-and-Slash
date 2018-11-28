package com.robertx22.database.stat_mods.flat.elemental.bonus;

import com.robertx22.database.stat_types.offense.spell_to_attack.WaterSpellToAttackDMG;
import com.robertx22.stats.Stat;

public class BonusWaterDamageFlat extends BaseBonusDamageFlat {

	public BonusWaterDamageFlat() {

	}

	@Override
	public Stat GetBaseStat() {
		return new WaterSpellToAttackDMG();
	}

	@Override
	public String GUID() {
		return "BonusWaterDamageFlat";
	}

}
