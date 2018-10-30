package com.robertx22.database.stats.mods.flat.elemental.bonus;

import com.robertx22.database.stats.types.offense.bonus.BonusFireDamage;
import com.robertx22.stats.Stat;

public class BonusFireDamageFlat extends BaseBonusDamageFlat {

	public BonusFireDamageFlat() {

	}

	@Override
	public Stat GetBaseStat() {
		return new BonusFireDamage();
	}

	@Override
	public String GUID() {
		return "BonusFireDamageFlat";
	}

}
