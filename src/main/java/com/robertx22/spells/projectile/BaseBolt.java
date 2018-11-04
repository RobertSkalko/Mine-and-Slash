package com.robertx22.spells.projectile;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;

public abstract class BaseBolt extends BaseSpell {

	@Override
	public int ManaCost() {
		return 10;
	}

	@Override
	public int BaseValue() {
		return 4;
	}

	@Override
	public String GetDescription(SpellItemData data) {
		return "Casts a Single Target elemental projectile";

	}

}
