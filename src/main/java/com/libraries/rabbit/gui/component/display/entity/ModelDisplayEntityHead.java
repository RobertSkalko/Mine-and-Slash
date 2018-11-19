package com.libraries.rabbit.gui.component.display.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelDisplayEntityHead extends ModelDisplayEntity {

	public ModelDisplayEntityHead() {
		super();
	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	@Override
	public void render(Entity entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_,
			float p_78088_6_, float scale) {
		// this seems to be the best way to handle this
		super.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, entityIn);
		if (((DisplayEntity) entityIn).getTextureHeight() > 32) {
			if (textureHeight == 32) {
				textureHeight = 64;
			}
			GlStateManager.pushMatrix();
			{
				GlStateManager.translate(0.35F, 1.5F, 0.0F);
				super.bipedHead.render(scale);
				super.bipedHeadwear.render(scale);
			}
			GlStateManager.popMatrix();
		} else {
			if (textureHeight != 32) {
				textureHeight = 32;
			}
			GlStateManager.pushMatrix();
			{
				GlStateManager.translate(0.35F, 1.5F, 0.0F);
				super.biped.bipedHead.render(scale);
				super.biped.bipedHeadwear.render(scale);
			}
			GlStateManager.popMatrix();
		}

	}

	@Override
	public void setModelAttributes(ModelBase model) {
		super.setModelAttributes(model);
	}
}
