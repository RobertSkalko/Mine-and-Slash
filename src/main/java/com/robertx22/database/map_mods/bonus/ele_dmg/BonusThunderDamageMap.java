package com.robertx22.database.map_mods.bonus.ele_dmg;

import com.robertx22.database.map_mods.bases.BonusEleDmgBase;
import com.robertx22.database.stat_types.elementals.spell_to_attack.ThunderSpellToAttackDMG;
import com.robertx22.stats.Stat;

public class BonusThunderDamageMap extends BonusEleDmgBase {

	public BonusThunderDamageMap() {
	}

	@Override
	public String GUID() {
		return "BonusThunderDamageMap";
	}

	@Override
	public Stat GetBaseStat() {
		return new ThunderSpellToAttackDMG();
	}

}