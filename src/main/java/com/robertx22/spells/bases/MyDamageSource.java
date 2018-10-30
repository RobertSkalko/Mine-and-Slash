package com.robertx22.spells.bases;

import net.minecraft.util.DamageSource;

public class MyDamageSource extends DamageSource {

	public MyDamageSource(String damageTypeIn) {
		super(damageTypeIn);
		this.setDamageBypassesArmor();
		this.setDamageIsAbsolute();
		this.setMagicDamage();

	}

}
