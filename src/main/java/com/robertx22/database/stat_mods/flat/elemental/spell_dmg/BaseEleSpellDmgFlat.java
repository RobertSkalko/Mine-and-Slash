package com.robertx22.database.stat_mods.flat.elemental.spell_dmg;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class BaseEleSpellDmgFlat extends StatMod {

	public BaseEleSpellDmgFlat() {
	}

	@Override
	public float Min() {
		return 2;
	}

	@Override
	public float Max() {
		return 10;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Flat;
	}

}
