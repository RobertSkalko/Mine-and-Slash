package com.robertx22.spells.bases;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.entity.EntityLivingBase;

public class DamageData {

	public DamageData(EntityLivingBase entity, SpellItemData spellItem) {
		// this.effect = e;
		this.caster = entity;
		this.spellItem = spellItem;

		this.casterUnit = UnitSaving.Load(entity);

	}

	public Unit casterUnit;
	// public BaseSpellEffect effect;
	public EntityLivingBase caster;
	public SpellItemData spellItem;

}
