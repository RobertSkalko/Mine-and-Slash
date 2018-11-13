package com.rabbit.gui.component.display.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDisplayEntity extends RenderLiving {

	public RenderDisplayEntity(RenderManager rendermanagerIn, ModelBase model, float shadowSize) {
		super(rendermanagerIn, model, shadowSize);
	}

	// this is the hitch here we need to mutate the texture
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return ((DisplayEntity) par1Entity).getTexture();
	}

}