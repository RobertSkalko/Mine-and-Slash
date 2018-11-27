package com.robertx22.database.stat_mods.flat.elemental.attack_dmg;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.stats.Stat;

public class AttackFireDamageFlat extends BaseEleAttackDmgFlat {

	public AttackFireDamageFlat() {
	}

	@Override
	public String GUID() {
		return "AttackFireDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new AttackFireDamage();
	}

}
