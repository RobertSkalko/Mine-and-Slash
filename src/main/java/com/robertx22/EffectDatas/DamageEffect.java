package com.robertx22.effectdatas;

import com.robertx22.effectdatas.interfaces.IArmorReducable;
import com.robertx22.effectdatas.interfaces.ICrittable;
import com.robertx22.effectdatas.interfaces.IDamageEffect;
import com.robertx22.effectdatas.interfaces.IElementalPenetrable;
import com.robertx22.effectdatas.interfaces.IElementalResistable;
import com.robertx22.effectdatas.interfaces.IPenetrable;
import com.robertx22.enumclasses.Elements;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.utilityclasses.HealthUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class DamageEffect extends EffectData
		implements IArmorReducable, IPenetrable, IDamageEffect, IElementalResistable, IElementalPenetrable, ICrittable {

	public DamageEffect(EntityLivingBase source, EntityLivingBase target, int dmg) {
		super(source, target);

		this.Number = dmg;

	}

	public static String DmgSourceName = Ref.MODID + "_Custom_Damage";
	public Elements Element;
	public int ArmorPene;
	public int ElementalPene;

	@Override
	protected void activate() {

		MyDamageSource dmgsource = new MyDamageSource(DmgSourceName);
		dmgsource.setDamageBypassesArmor();
		// dmgsource.setDamageIsAbsolute();

		float dmg = HealthUtils.DamageToMinecraftHealth(Number, Target);

		Target.attackEntityFrom(dmgsource, dmg);

		System.out.println("dealt dmg :" + dmg);

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

	public boolean crit = false;

	@Override
	public void SetCrit(boolean bool) {
		crit = bool;

	}

	@Override
	public boolean GetCrit() {
		return crit;
	}

}
