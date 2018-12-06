package com.robertx22.database.map_mods.minus.all_ele_dmg;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class BaseAllEleDmgMap extends StatMod {

	public BaseAllEleDmgMap() {
	}

	@Override
	public float Min() {
		return -30;
	}

	@Override
	public float Max() {
		return -75;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

}