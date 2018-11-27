package com.robertx22.database.stat_mods.flat.elemental.bonus;

import com.robertx22.database.stat_types.offense.conversion.FireDamageConversion;
import com.robertx22.stats.Stat;

public class BonusFireDamageFlat extends BaseBonusDamageFlat {

	public BonusFireDamageFlat() {

	}

	@Override
	public Stat GetBaseStat() {
		return new FireDamageConversion();
	}

	@Override
	public String GUID() {
		return "BonusFireDamageFlat";
	}

}
