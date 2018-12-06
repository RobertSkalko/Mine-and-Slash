package com.robertx22.database.stat_mods.flat.elemental.bonus;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class BaseSpellToAttackFlat extends StatMod {

	public BaseSpellToAttackFlat() {
	}

	@Override
	public float Min() {
		return 3;
	}

	@Override
	public float Max() {
		return 15;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

}