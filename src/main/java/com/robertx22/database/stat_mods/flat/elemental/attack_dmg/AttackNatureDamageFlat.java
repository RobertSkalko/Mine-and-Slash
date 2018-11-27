package com.robertx22.database.stat_mods.flat.elemental.attack_dmg;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.stats.Stat;

public class AttackNatureDamageFlat extends BaseEleAttackDmgFlat {

	public AttackNatureDamageFlat() {
	}

	@Override
	public String GUID() {
		return "AttackNatureDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new AttackNatureDamage();
	}

}
