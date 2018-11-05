package com.robertx22.saveclasses;

import java.io.Serializable;

import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.entity.EntityLivingBase;

public class DamageNumberData implements Serializable {

	private static final long serialVersionUID = 971083230667977081L;

	public Elements element;
	public String string;
	public double x;
	public double y;
	public double z;
	public float height;

	public DamageNumberData(String str, Elements element, EntityLivingBase entity) {

		this.element = element;
		this.string = str;
		this.x = entity.posX;
		this.y = entity.posY;
		this.z = entity.posZ;
		this.height = entity.height;
	}
}
