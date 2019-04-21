package com.robertx22.customitems;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * ModelScorgerShellSheild - bagu_chan Created using Tabula 7.0.0
 */
public class ModelShield extends ModelBase {
    public ModelRenderer plate;
    public ModelRenderer plate2;
    public ModelRenderer plate3;
    public ModelRenderer plate4;
    public ModelRenderer plate5;

    public ModelShield() {
	this.textureWidth = 64;
	this.textureHeight = 64;
	this.plate2 = new ModelRenderer(this, 24, 0);
	this.plate2.setRotationPoint(5.0F, 0.0F, -2.0F);
	this.plate2.addBox(0.0F, -5.0F, 0.0F, 4, 10, 2, 0.0F);
	this.setRotateAngle(plate2, 0.0F, -0.5539675045830001F, 0.0F);
	this.plate4 = new ModelRenderer(this, 0, 12);
	this.plate4.setRotationPoint(0.0F, -4.0F, -1.1F);
	this.plate4.addBox(-5.0F, -3.0F, -1.0F, 10, 3, 2, 0.0F);
	this.setRotateAngle(plate4, -0.5539675045830001F, 0.0F, 0.0F);
	this.plate3 = new ModelRenderer(this, 24, 0);
	this.plate3.setRotationPoint(-5.0F, 0.0F, -2.0F);
	this.plate3.addBox(-4.0F, -5.0F, 0.0F, 4, 10, 2, 0.0F);
	this.setRotateAngle(plate3, 0.0F, 0.5539675045830001F, 0.0F);
	this.plate5 = new ModelRenderer(this, 0, 12);
	this.plate5.setRotationPoint(0.0F, 4.0F, -1.1F);
	this.plate5.addBox(-5.0F, 0.0F, -1.0F, 10, 3, 2, 0.0F);
	this.setRotateAngle(plate5, 0.5539675045830001F, 0.0F, 0.0F);
	this.plate = new ModelRenderer(this, 0, 0);
	this.plate.setRotationPoint(0.0F, 0.0F, 0.0F);
	this.plate.addBox(-5.0F, -5.0F, -2.0F, 10, 10, 2, 0.0F);
	this.plate.addChild(this.plate2);
	this.plate.addChild(this.plate4);
	this.plate.addChild(this.plate3);
	this.plate.addChild(this.plate5);
    }

    public void render() {
	this.plate.render(0.0625F);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
	modelRenderer.rotateAngleX = x;
	modelRenderer.rotateAngleY = y;
	modelRenderer.rotateAngleZ = z;
    }
}