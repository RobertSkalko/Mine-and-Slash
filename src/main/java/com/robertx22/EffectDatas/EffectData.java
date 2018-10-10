package com.robertx22.EffectDatas;

import java.awt.List;

import net.minecraft.entity.Entity;

public abstract class EffectData {
	public Entity Source;
	public Entity Target;

	public float Number;

	public abstract void Activate();

}