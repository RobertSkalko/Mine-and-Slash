package com.robertx22.database.stats.mods.flat.elemental.attack_dmg;

import com.robertx22.database.stats.types.elementals.attack_damage.AttackNatureDamage;
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
