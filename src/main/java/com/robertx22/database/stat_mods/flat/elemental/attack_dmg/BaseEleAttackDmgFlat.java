package com.robertx22.database.stat_mods.flat.elemental.attack_dmg;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class BaseEleAttackDmgFlat extends StatMod {

	public BaseEleAttackDmgFlat() {
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
