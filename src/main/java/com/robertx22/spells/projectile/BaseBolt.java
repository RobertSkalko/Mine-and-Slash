package com.robertx22.spells.projectile;

import com.robertx22.spells.bases.BaseSpell;

public abstract class BaseBolt extends BaseSpell {

	@Override
	public int ManaCost() {
		return 10;
	}

	@Override
	public int Cooldown() {
		return 10;
	}

	@Override
	public int BaseDamage() {
		return 2;
	}
}
