package com.rabbit.gui.component.display.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelDisplayEntity extends ModelPlayer {

	// these are for 64x32 textures
	protected ModelBiped biped;

	public ModelDisplayEntity() {
		super(0, false);
		biped = new ModelBiped();
		biped.isChild = false;
	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	@Override
	public void render(Entity entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_,
			float p_78088_6_, float scale) {

		GlStateManager.pushMatrix();
		{

			if (((DisplayEntity) entityIn).getTextureHeight() > 32) {
				if (textureHeight == 32) {
					textureHeight = 64;
				}
				setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, entityIn);
				super.render(entityIn, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale);
			} else {
				biped.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, entityIn);
				biped.render(entityIn, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale);
			}
		}
		GlStateManager.popMatrix();
	}
}
