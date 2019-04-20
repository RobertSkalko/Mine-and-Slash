package com.robertx22.spells.bases;

import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class MyDamageSource extends EntityDamageSource {

    public Elements element = Elements.None;
    public int realDamage = 0;

    public MyDamageSource(String damageTypeIn, Entity source, Elements element, int dmg) {
	super(damageTypeIn, source);
	// this.setDamageBypassesArmor();
	this.setDamageIsAbsolute();
	this.element = element;
	realDamage = dmg;

    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {

	try {
	    return new TextComponentString(entityLivingBaseIn.getDisplayName().getFormattedText() + " ")
		    .appendSibling(SLOC.chat("has_died_by_the_hands_of"))
		    .appendText(" " + this.damageSourceEntity.getDisplayName().getFormattedText());
	} catch (Exception e) {
	    e.printStackTrace();

	    return new TextComponentString("");
	}

    }

}
