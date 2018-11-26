package com.robertx22.database.stats.mods.flat.elemental.attack_dmg;

import com.robertx22.database.stats.types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.stats.Stat;

public class AttackWaterDamageFlat extends BaseEleAttackDmgFlat {

	public AttackWaterDamageFlat() {
	}

	@Override
	public String GUID() {
		return "AttackWaterDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new AttackWaterDamage();
	}

}
