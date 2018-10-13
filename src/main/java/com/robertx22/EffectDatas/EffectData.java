package com.robertx22.effectdatas;

import com.robertx22.saveclasses.Unit;
import com.robertx22.saving.Saving;

import net.minecraft.entity.EntityLivingBase;

public abstract class EffectData {
	public EntityLivingBase Source;
	public EntityLivingBase Target;

	public int Number;

	public Unit GetSource() {

		return Saving.Load(Source, Unit.class);
	}

	public Unit GetTarget() {
		return Saving.Load(Target, Unit.class);
	}

	public abstract void Activate();

}