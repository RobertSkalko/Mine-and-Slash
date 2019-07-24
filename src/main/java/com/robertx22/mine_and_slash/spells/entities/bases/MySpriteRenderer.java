package com.robertx22.mine_and_slash.spells.entities.bases;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class MySpriteRenderer<T extends Entity & IMyRenderAsItem> extends EntityRenderer<T> {
    private final net.minecraft.client.renderer.ItemRenderer itemRenderer;
    private final float scale;

    public MySpriteRenderer(EntityRendererManager p_i50956_1_,
                            net.minecraft.client.renderer.ItemRenderer p_i50956_2_,
                            float p_i50956_3_) {
        super(p_i50956_1_);
        this.itemRenderer = p_i50956_2_;
        this.scale = p_i50956_3_;
    }

    public MySpriteRenderer(EntityRendererManager p_i50957_1_,
                            net.minecraft.client.renderer.ItemRenderer p_i50957_2_) {
        this(p_i50957_1_, p_i50957_2_, 1.0F);
    }

    public void doRender(T entity, double x, double y, double z, float entityYaw,
                         float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translatef((float) x, (float) y, (float) z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scalef(this.scale, this.scale, this.scale);
        GlStateManager.rotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
        this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
        }

        this.itemRenderer.renderItem(((IMyRenderAsItem) entity).getItem(), ItemCameraTransforms.TransformType.GROUND);
        if (this.renderOutlines) {
            GlStateManager.tearDownSolidRenderingTextureCombine();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
    }
}