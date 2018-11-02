package com.robertx22.spells.projectile;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;

public abstract class BaseBoltAOE extends BaseSpell {

	@Override
	public int ManaCost() {
		return 25;
	}

	@Override
	public int BaseValue() {
		return 4;
	}

	@Override
	public String GetDescription(SpellItemData data) {
		return "Casts an elemental projectile that does AOE dmg on hit.";

	}

}
