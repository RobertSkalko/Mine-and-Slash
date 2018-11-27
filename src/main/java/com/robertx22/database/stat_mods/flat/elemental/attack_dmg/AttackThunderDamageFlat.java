package com.robertx22.database.stat_mods.flat.elemental.attack_dmg;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.stats.Stat;

public class AttackThunderDamageFlat extends BaseEleAttackDmgFlat {

	public AttackThunderDamageFlat() {
	}

	@Override
	public String GUID() {
		return "AttackThunderDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new AttackThunderDamage();
	}

}
