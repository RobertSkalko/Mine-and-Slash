package com.robertx22.database.stats.mods.flat;

import com.robertx22.database.stats.types.Health;
import com.robertx22.enums.StatTypes;
import com.robertx22.stats.StatMod;

public class HealthFlat extends StatMod {

	public HealthFlat() {}
	
	@Override
	public String BaseClass() {
		return Health.class.toString();
	}

	@Override
	public int Min() {
		return 5;
	}

	@Override
	public int Max() {
		return 15;
	}
	
	@Override	
	public StatTypes Type() {
		return StatTypes.Flat;
	}
}
