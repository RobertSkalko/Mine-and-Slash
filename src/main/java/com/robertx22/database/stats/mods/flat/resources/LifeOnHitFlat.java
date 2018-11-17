package com.robertx22.database.stats.mods.flat.resources;

import com.robertx22.database.stats.types.resources.LifeOnHit;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public class LifeOnHitFlat extends StatMod {

	public LifeOnHitFlat() {
	}

	@Override
	public String GUID() {
		return "LifeOnHitFlat";
	}

	@Override
	public int Min() {
		return 1;
	}

	@Override
	public int Max() {
		return 3;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

	@Override
	public Stat GetBaseStat() {
		return new LifeOnHit();
	}

	@Override
	public int Weight() {
		return IWeighted.UncommonWeight;
	}
}