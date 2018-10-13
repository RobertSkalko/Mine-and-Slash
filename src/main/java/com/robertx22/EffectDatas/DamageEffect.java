package com.robertx22.effectdatas;

import com.robertx22.effectdatas.interfaces.IArmorReducable;
import com.robertx22.effectdatas.interfaces.IDamageEffect;
import com.robertx22.effectdatas.interfaces.IElementalPenetrable;
import com.robertx22.effectdatas.interfaces.IElementalResistable;
import com.robertx22.effectdatas.interfaces.IPenetrable;
import com.robertx22.enums.Elements;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class DamageEffect extends EffectData
		implements IArmorReducable, IPenetrable, IDamageEffect, IElementalResistable, IElementalPenetrable {

	private static String DmgSourceName = "Custom Damage";
	public Elements Element;
	public int ArmorPene;
	public int ElementalPene;

	@Override
	public void Activate() {

		DamageSource dmgsource = new DamageSource(DmgSourceName);

		Target.attackEntityFrom(dmgsource, 1); // todo

	}

	@Override
	public EntityLivingBase Source() {
		return Source;
	}

	@Override
	public EntityLivingBase Target() {
		return Target;
	}

	@Override
	public int Number() {
		return Number;
	}

	@Override
	public Elements GetElement() {
		return Element;
	}

	@Override
	public void SetArmorPenetration(int val) {
		this.ArmorPene = val;

	}

	@Override
	public void SetElementalPenetration(int val) {
		this.ElementalPene = val;
	}

	@Override
	public int GetArmorPenetration() {
		return this.ArmorPene;
	}

}
