package com.robertx22.database.stats.mods.flat.elemental.bonus;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class BaseBonusDamageFlat extends StatMod {

	public BaseBonusDamageFlat() {
	}

	@Override
	public int Min() {
		return 1;
	}

	@Override
	public int Max() {
		return 7;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

}