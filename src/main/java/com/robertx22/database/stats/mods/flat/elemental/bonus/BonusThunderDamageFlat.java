package com.robertx22.database.stats.mods.flat.elemental.bonus;

import com.robertx22.database.stats.types.offense.bonus.BonusThunderDamage;
import com.robertx22.stats.Stat;

public class BonusThunderDamageFlat extends BaseBonusDamageFlat {

	public BonusThunderDamageFlat() {

	}

	@Override
	public Stat GetBaseStat() {
		return new BonusThunderDamage();
	}

	@Override
	public String GUID() {
		return "BonusThunderDamageFlat";
	}

}
