package com.robertx22.saveclasses;

import java.io.Serializable;

import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.entity.EntityLivingBase;

public class DamageNumberData implements Serializable {

	private static final long serialVersionUID = 971083230667977081L;

	public Elements element;
	public int number;
	public double x;
	public double y;
	public double z;
	public float height;

	public DamageNumberData(MyDamageSource source, EntityLivingBase entity) {

		this.element = source.element;
		this.number = source.realDamage;
		this.x = entity.posX;
		this.y = entity.posY;
		this.z = entity.posZ;
		this.height = entity.height;
	}
}
