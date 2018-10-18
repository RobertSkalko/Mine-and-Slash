package com.robertx22.saveclasses;

import com.robertx22.spells.bases.BaseSpellEffect;

import net.minecraft.entity.EntityLivingBase;

public class DamageData {

	public DamageData(EntityLivingBase entity, BaseSpellEffect e) {
		this.effect = e;
		this.caster = entity;
	}

	public BaseSpellEffect effect;
	public EntityLivingBase caster;

}
