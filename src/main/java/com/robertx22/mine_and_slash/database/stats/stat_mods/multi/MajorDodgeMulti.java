package com.robertx22.mine_and_slash.database.stats.stat_mods.multi;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.defense.Dodge;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class MajorDodgeMulti extends StatMod {

	public MajorDodgeMulti() {
	}

	@Override
	public String GUID() {
		return "MajorDodgeMulti";
	}

	@Override
	public float Min() {
		return 20;
	}

	@Override
	public float Max() {
		return 50;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Multi;
	}

	@Override
	public Stat GetBaseStat() {
		return new Dodge();
	}

}
