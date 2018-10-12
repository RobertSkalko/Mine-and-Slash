package com.robertx22.database.stats.mods.flat;

import com.robertx22.database.stats.types.Armor;
import com.robertx22.enums.StatTypes;
import com.robertx22.stats.StatMod;

public class ArmorFlat extends StatMod {

	public ArmorFlat() {
	}

	@Override
	public String BaseClass() {
		return Armor.class.toString();
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
