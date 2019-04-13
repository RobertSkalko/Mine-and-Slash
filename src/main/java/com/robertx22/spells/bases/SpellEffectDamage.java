package com.robertx22.spells.bases;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.entity.EntityLivingBase;

public class SpellEffectDamage extends BaseSpellEffect {

    public SpellEffectDamage() {
	super();

    }

    public SpellEffectDamage(Elements el) {
	super();

	this.element = el;
    }

    Elements element = Elements.None;

    @Override
    public void Activate(DamageData dmgdata, EntityLivingBase target) {
	DamageEffect dmg = new DamageEffect(dmgdata.caster, target, dmgdata.spellItem.GetDamage(dmgdata.casterUnit));
	dmg.Element = this.element;
	dmg.Activate();

    }

    @Override
    public String Name() {
	return "Spell Effect Damage";
    }

}