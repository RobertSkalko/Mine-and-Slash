package com.robertx22.database.stats.mods.flat;

import com.robertx22.enums.StatRefs;
import com.robertx22.enums.StatTypes;
import com.robertx22.stats.StatMod;

public class HealthFlat extends StatMod {

	@Override
	public StatRefs StatRef() {
		return StatRefs.Health;
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
