package com.robertx22.mine_and_slash.a_libraries;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class TestNameRender<T extends LivingEntity> extends LayerRenderer<T, EntityModel<T>> {

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
        Minecraft mc = Minecraft.getInstance();
        float scale = 0.026666672F;

        double distance = renderManager.squareDistanceTo(en);
        if (!(distance > 4096.0D)) {
            boolean discrete = !en.isDiscrete();
            float height = 0.5F;
            matrix.push();

            //Quaternion rotation = renderManager.info.getUpVector().rotation(360);

            //matrix.rotate(rotation);
            matrix.rotate(Vector3f.ZP.rotationDegrees(180));

            matrix.translate(0.0D, (double) height, 0.0D);

            RenderSystem.normal3f(0.0F, 1.0F, 0.0F);

            matrix.scale(-scale, -scale, -scale);

            Matrix4f matrix4f = matrix.getLast().getPositionMatrix();
            float f1 = Minecraft.getInstance().gameSettings.getTextBackgroundOpacity(0.25F);
            int j = (int) (f1 * 255.0F) << 24;

            FontRenderer fontRenderer = renderManager.getFontRenderer();
            float f2 = (float) (-fontRenderer.getStringWidth(displayNameIn) / 2);

            //fontRenderer.drawStringWithShadow("TEST", 0, 0, TextFormatting.GREEN.getColor());

            int color = 0;

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