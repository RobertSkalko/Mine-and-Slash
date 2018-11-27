package com.robertx22.database.stat_mods.flat.elemental.bonus;

import com.robertx22.database.stat_types.offense.conversion.NatureDamageConversion;
import com.robertx22.stats.Stat;

public class BonusNatureDamageFlat extends BaseBonusDamageFlat {

	public BonusNatureDamageFlat() {

	}

	@Override
	public Stat GetBaseStat() {
		return new NatureDamageConversion();
	}

	@Override
	public String GUID() {
		return "BonusNatureDamageFlat";
	}

}
