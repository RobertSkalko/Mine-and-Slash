package com.robertx22.effectdatas;

import com.robertx22.effectdatas.interfaces.IBuffableSpell;
import com.robertx22.effectdatas.interfaces.IBuffableSpell.SpellBuffType;

import net.minecraft.entity.EntityLivingBase;

public class SpellBuffEffect extends EffectData {

    private IBuffableSpell buffable;

    private SpellBuffType buff = SpellBuffType.None;

    public SpellBuffEffect(EntityLivingBase source, EntityLivingBase target, IBuffableSpell buffable) {
	super(source, target);
	this.buffable = buffable;
    }

    @Override
    protected void activate() {
	buffable.setBuff(buff);
    }

}
