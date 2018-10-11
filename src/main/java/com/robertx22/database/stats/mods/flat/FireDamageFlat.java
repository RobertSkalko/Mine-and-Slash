package com.robertx22.database.stats.mods.flat;

import com.robertx22.enums.StatRefs;
import com.robertx22.enums.StatTypes;
import com.robertx22.stats.StatMod;

public class FireDamageFlat extends StatMod {

	@Override
	public StatRefs StatRef() {
		return StatRefs.Fire_Damage;
	}

	@Override
	public int Min() {
		return 2;
	}

	@Override
	public int Max() {
		return 10;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	

}
