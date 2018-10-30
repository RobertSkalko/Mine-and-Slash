package com.robertx22.database.stats.mods.percent;

import com.robertx22.database.StatModAnot;
import com.robertx22.database.stats.types.resources.ManaRegen;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

@StatModAnot
public class ManaRegenPercent extends StatMod {

	public ManaRegenPercent() {
	}

	@Override
	public String GUID() {
		return "ManaRegenPercent";
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
		return StatTypes.Percent;
	}

	@Override
	public Stat GetBaseStat() {
		return new ManaRegen();
	}

}
