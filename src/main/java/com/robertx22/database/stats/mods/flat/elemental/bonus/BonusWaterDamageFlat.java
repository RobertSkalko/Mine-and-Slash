package com.robertx22.database.stats.mods.flat.elemental.bonus;

import com.robertx22.database.stats.types.offense.conversion.WaterDamageConversion;
import com.robertx22.stats.Stat;

public class BonusWaterDamageFlat extends BaseBonusDamageFlat {

	public BonusWaterDamageFlat() {

	}

	@Override
	public Stat GetBaseStat() {
		return new WaterDamageConversion();
	}

	@Override
	public String GUID() {
		return "BonusWaterDamageFlat";
	}

}
