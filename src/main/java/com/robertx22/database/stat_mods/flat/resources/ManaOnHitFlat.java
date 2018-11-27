package com.robertx22.database.stat_mods.flat.resources;

import com.robertx22.database.stat_types.resources.ManaOnHit;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public class ManaOnHitFlat extends StatMod {

	public ManaOnHitFlat() {
	}

	@Override
	public String GUID() {
		return "ManaOnHitFlat";
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
		return new ManaOnHit();
	}

	@Override
	public int Weight() {
		return IWeighted.UncommonWeight;
	}
}