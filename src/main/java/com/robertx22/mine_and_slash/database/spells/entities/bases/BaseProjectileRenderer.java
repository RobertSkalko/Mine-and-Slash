package com.robertx22.mine_and_slash.database.spells.entities.bases;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public abstract class BaseProjectileRenderer<T extends Entity> extends EntityRenderer<T> {

    private Model model;

    public BaseProjectileRenderer(EntityRendererManager manager, Model model) {
        super(manager);
        this.model = model;
    }

    @Override
    public void render(T en, float f1, float f2, MatrixStack matrix, IRenderTypeBuffer buffer, int int1) {

        matrix.push();
        matrix.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(f2, en.prevRotationYaw, en.rotationYaw) - 90.0F));
        matrix.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(f2, en.prevRotationPitch, en.rotationPitch) + 90.0F));
        IVertexBuilder builder = ItemRenderer.getBuffer(buffer, this.model.getRenderType(this.getEntityTexture(en)),
                                                        false, true
        );

        this.model.render(matrix, builder, int1, OverlayTexture.DEFAULT_LIGHT, 1.0F, 1.0F, 1.0F, 1.0F);
        matrix.pop();
        super.render(en, f1, f2, matrix, buffer, int1);
    }

}
