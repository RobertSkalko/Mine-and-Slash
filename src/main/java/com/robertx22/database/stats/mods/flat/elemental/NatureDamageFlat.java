package com.robertx22.database.stats.mods.flat.elemental;

import com.robertx22.database.stats.types.elementals.damage.NatureDamage;
import com.robertx22.enumclasses.StatTypes;
import com.robertx22.interfaces.IWeighted;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;

public class NatureDamageFlat extends StatMod {

	public NatureDamageFlat() {
	}

	@Override
	public String GUID() {
		return "NatureDamageFlat";
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
		return new NatureDamage();
	}

	@Override
	public int Weight() {
		return IWeighted.NormalWeight;
	}

}
