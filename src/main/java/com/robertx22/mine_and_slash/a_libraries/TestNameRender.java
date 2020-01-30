package com.robertx22.mine_and_slash.a_libraries;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;

public class TestNameRender<T extends Entity> extends LayerRenderer<T, EntityModel<T>> {

    public TestNameRender(IEntityRenderer<T, EntityModel<T>> renderer) {
        super(renderer);

    }

    @Override
    public void render(MatrixStack matrix, IRenderTypeBuffer buffer, int lights, T en, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        //renderName(en, "Test name", matrix, buffer, lights);

    }

    protected void renderName(T en, String displayNameIn, MatrixStack matrix, IRenderTypeBuffer buffer, int lights) {

        EntityRendererManager renderManager = Minecraft.getInstance().getRenderManager();

        double distance = renderManager.squareDistanceTo(en);
        if (!(distance > 4096.0D)) {
            boolean discrete = !en.isDiscrete();
            float height = en.getHeight() + 0.5F;
            int color = 0;
            matrix.push();
            matrix.translate(0.0D, (double) height, 0.0D);

            Quaternion rotation = renderManager.getCameraOrientation();

            matrix.rotate(rotation);

            matrix.scale(-0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = matrix.getLast().getPositionMatrix();
            float f1 = Minecraft.getInstance().gameSettings.getTextBackgroundOpacity(0.25F);
            int j = (int) (f1 * 255.0F) << 24;

            FontRenderer fontRenderer = renderManager.getFontRenderer();
            float f2 = (float) (-fontRenderer.getStringWidth(displayNameIn) / 2);
            fontRenderer.renderString(
                    displayNameIn, f2, (float) color, 553648127, false, matrix4f, buffer, discrete, j, lights);
            if (discrete) {
                fontRenderer.renderString(
                        displayNameIn, f2, (float) color, -1, false, matrix4f, buffer, false, 0, lights);
            }

            matrix.pop();
        }
    }

}