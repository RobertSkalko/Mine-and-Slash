package com.robertx22.spells.bases;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class MyDamageSource extends EntityDamageSource {

	public MyDamageSource(String damageTypeIn, Entity source) {
		super(damageTypeIn, source);
		this.setDamageBypassesArmor();
		this.setDamageIsAbsolute();
		this.setMagicDamage();

	}

}
