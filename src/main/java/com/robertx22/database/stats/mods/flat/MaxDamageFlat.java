package com.robertx22.database.stats.mods.flat;

import com.robertx22.database.stats.types.Damage;
import com.robertx22.database.stats.types.MaxDamage;
import com.robertx22.enums.StatTypes;
import com.robertx22.stats.RangeStatMod;

public class MaxDamageFlat extends RangeStatMod {

	public MaxDamageFlat() {
	}

	@Override
	public String BaseClass() {
		return MaxDamage.class.toString();
	}

	@Override
	public int Min() {
		return 7;
	}

	@Override
	public int Max() {
		return 15;
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
