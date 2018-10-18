package com.robertx22.spells.frostbolt;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.enumclasses.Elements;
import com.robertx22.spells.bases.BaseSpellEffect;
import com.robertx22.spells.bases.DamageData;

import net.minecraft.entity.EntityLivingBase;

public class EffectFrostBolt extends BaseSpellEffect {

	public EffectFrostBolt() {
		super();

	}

	@Override
	public String Name() {
		return "Frost Bolt Damage";
	}

	@Override
	public void Activate(DamageData dmgdata, EntityLivingBase target) {

		DamageEffect dmg = new DamageEffect(dmgdata.caster, target, dmgdata.spellItem.GetDamage(dmgdata.casterUnit));

		System.out.println("Dmg is " + dmg.Number);
		dmg.Element = Elements.Water;

		// dmg.Number = 10;
		dmg.SetCrit(true);

		dmg.Activate();

	}

}
