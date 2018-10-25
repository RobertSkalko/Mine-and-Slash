package com.robertx22.database.stats.mods;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class BaseTraitMod extends StatMod {

	public BaseTraitMod() {
	}

	@Override
	public int Min() {
		return 1;
	}

	@Override
	public int Max() {
		return 1;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

}