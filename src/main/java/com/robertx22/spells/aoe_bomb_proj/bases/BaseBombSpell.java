package com.robertx22.spells.aoe_bomb_proj.bases;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.projectile.BaseSpellProjectile;
import com.robertx22.uncommon.CLOC;

public abstract class BaseBombSpell extends BaseSpellProjectile {

    @Override
    public String GetDescription(SpellItemData data) {
	return CLOC.tooltip("aoe_bomb_spell");

    }

    @Override
    public int ManaCost() {
	return 20;
    }

    public float damageScaling = 0.5F;

}
