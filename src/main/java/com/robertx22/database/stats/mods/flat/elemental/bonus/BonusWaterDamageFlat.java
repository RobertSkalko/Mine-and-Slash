package com.robertx22.database.stats.mods.flat.elemental.bonus;

import com.robertx22.database.stats.types.offense.bonus.BonusWaterDamage;
import com.robertx22.stats.Stat;

public class BonusWaterDamageFlat extends BaseBonusDamageFlat {

	public BonusWaterDamageFlat() {

	}

	@Override
	public Stat GetBaseStat() {
		return new BonusWaterDamage();
	}

	@Override
	public String GUID() {
		return "BonusWaterDamageFlat";
	}

}
