package com.robertx22.database.stats.mods.flat.elemental;

import com.robertx22.classes.IWeighted;
import com.robertx22.database.stats.types.elementals.damage.WaterDamage;
import com.robertx22.enumclasses.StatTypes;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;

public class WaterDamageFlat extends StatMod {

	public WaterDamageFlat() {
	}

	@Override
	public String GUID() {
		return "WaterDamageFlat";
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
		return new WaterDamage();
	}

	@Override
	public int Weight() {
		return IWeighted.NormalWeight;
	}

}
