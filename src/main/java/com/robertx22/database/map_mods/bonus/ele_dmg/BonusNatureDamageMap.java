package com.robertx22.database.map_mods.bonus.ele_dmg;

import com.robertx22.database.map_mods.bases.BonusEleDmgBase;
import com.robertx22.database.stat_types.elementals.spell_to_attack.NatureSpellToAttackDMG;
import com.robertx22.stats.Stat;

public class BonusNatureDamageMap extends BonusEleDmgBase {

	public BonusNatureDamageMap() {
	}

	@Override
	public String GUID() {
		return "BonusNatureDamageMap";
	}

	@Override
	public Stat GetBaseStat() {
		return new NatureSpellToAttackDMG();
	}

}