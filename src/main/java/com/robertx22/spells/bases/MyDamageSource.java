package com.robertx22.spells.bases;

import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class MyDamageSource extends EntityDamageSource {

	public Elements element = Elements.None;
	public int realDamage = 0;

	public MyDamageSource(String damageTypeIn, Entity source, Elements element, int dmg) {
		super(damageTypeIn, source);
		this.setDamageBypassesArmor();
		this.setDamageIsAbsolute();
		this.setMagicDamage();
		this.element = element;
		realDamage = dmg;

	}

}
