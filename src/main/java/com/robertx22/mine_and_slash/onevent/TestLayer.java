package com.robertx22.mine_and_slash.onevent;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.items.gearitems.offhands.NormalShield;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nonnull;

public class TestLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
    public TestLayer(LivingRenderer renderer) {
        super(renderer);
    }

    public void render(@Nonnull LivingEntity livingEntity, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {

        float r = 0.5F;
        float n = 70;

        GlStateManager.translatef(r, 0, r);
        GlStateManager.rotatef(n, 0, 1, 0);
        renderOne(livingEntity);

        GlStateManager.translatef(r, 0, r);
        GlStateManager.rotatef(n, 0, 1, 0);
        renderOne(livingEntity);

        /*
        GlStateManager.translatef(0.5F, 0, -0.5F);
        GlStateManager.rotatef(90, 0, 1, 0);
        renderOne(livingEntity);



         */

    }

    private void renderOne(LivingEntity livingEntity) {

        ShieldModel modelShield = new ShieldModel();
        NormalShield shield = (NormalShield) NormalShield.Items.get(0);
        Minecraft.getInstance().getTextureManager().bindTexture(shield.resource);
        float rscale = livingEntity.getRenderScale();
        GlStateManager.pushMatrix();
        GlStateManager.scaled(1F * rscale, 1 * rscale, -1.0 * rscale);
        modelShield.render();
        GlStateManager.popMatrix();

    }

    public boolean shouldCombineTextures() {
        return false;
    }
}
