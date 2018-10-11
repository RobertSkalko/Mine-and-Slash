package com.robertx22.effectdatas;

import net.minecraft.entity.Entity;

public abstract class EffectData {
	public Entity Source;
	public Entity Target;

	public float Number;

	public abstract void Activate();

}