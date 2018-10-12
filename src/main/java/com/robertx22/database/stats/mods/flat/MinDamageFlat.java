package com.robertx22.database.stats.mods.flat;

import com.robertx22.database.stats.types.Damage;
import com.robertx22.database.stats.types.MinDamage;
import com.robertx22.enums.StatTypes;
import com.robertx22.stats.RangeStatMod;

public class MinDamageFlat extends RangeStatMod {

	public MinDamageFlat() {
	}

	@Override
	public String BaseClass() {
		return MinDamage.class.toString();
	}

	@Override
	public int Min() {
		return 2;
	}

	@Override
	public int Max() {
		return 6;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public String RangeBaseClass() {
		return Damage.class.toString();
	}
}
