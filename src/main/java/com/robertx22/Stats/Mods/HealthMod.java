package com.robertx22.Stats.Mods;

import com.robertx22.Enums.StatRefs;
import com.robertx22.Stats.StatMod;

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
