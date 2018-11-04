package com.robertx22.database.stats.mods.flat.resources;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.types.offense.LifeOnHit;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

@StatModAnot
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
		return IWeighted.NormalWeight;
	}
}