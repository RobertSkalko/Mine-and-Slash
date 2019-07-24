package com.robertx22.mine_and_slash.database.stats.stat_mods.percent;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.LifeOnHit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LifeOnHitPercent extends StatMod {

	public LifeOnHitPercent() {
	}

	@Override
	public String GUID() {
		return "LifeOnHitPercent";
	}

	@Override
	public float Min() {
		return 10;
	}

	@Override
	public float Max() {
		return 25;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Percent;
	}

	@Override
	public Stat GetBaseStat() {
		return new LifeOnHit();
	}

}
