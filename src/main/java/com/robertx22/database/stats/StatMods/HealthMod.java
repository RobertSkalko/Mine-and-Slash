package com.robertx22.database.stats.StatMods;

import com.robertx22.Enums.StatRefs;
import com.robertx22.stats.StatMod;

public class HealthMod extends StatMod {

	@Override
	public StatRefs StatRef() {	
		return StatRefs.Health;
	}

	@Override
	public int Min() {
		return  5;
	}

	@Override
	public int Max() {
		return  15;
	}

}
