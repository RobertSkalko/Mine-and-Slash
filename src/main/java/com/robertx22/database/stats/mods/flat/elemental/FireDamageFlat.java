package com.robertx22.database.stats.mods.flat.elemental;

import com.robertx22.classes.IWeighted;
import com.robertx22.database.stats.types.elementals.damage.FireDamage;
import com.robertx22.enumclasses.StatTypes;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;

public class FireDamageFlat extends StatMod {

	public FireDamageFlat() {
	}

	@Override
	public String GUID() {
		return "FireDamageFlat";
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

	@Override
	public Stat GetBaseStat() {
		return new FireDamage();
	}

	@Override
	public int Weight() {
		return IWeighted.NormalWeight;
	}

}
