package com.robertx22.mine_and_slash.database.spells.entities.bases;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.util.ResourceLocation;

public class MySpriteRenderer<T extends Entity> extends EntityRenderer<T> {
    private final ItemRenderer itemRenderer;
    private final float scale;
    private final boolean field_229126_f_;

    public MySpriteRenderer(EntityRendererManager p_i226035_1_, ItemRenderer p_i226035_2_,
                            float p_i226035_3_, boolean p_i226035_4_) {
        super(p_i226035_1_);
        this.itemRenderer = p_i226035_2_;
        this.scale = p_i226035_3_;
        this.field_229126_f_ = p_i226035_4_;
    }

    public MySpriteRenderer(EntityRendererManager p_i50957_1_, ItemRenderer p_i50957_2_) {
        this(p_i50957_1_, p_i50957_2_, 1.0F, false);
    }

    protected int func_225624_a_(T p_225624_1_, float p_225624_2_) {
        return this.field_229126_f_ ? 15 : super.func_225624_a_(p_225624_1_, p_225624_2_);
    }

    public void func_225623_a_(T p_225623_1_, float p_225623_2_, float p_225623_3_,
                               MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_,
                               int p_225623_6_) {
        p_225623_4_.func_227860_a_();
        p_225623_4_.func_227862_a_(this.scale, this.scale, this.scale);
        p_225623_4_.func_227863_a_(this.renderManager.func_229098_b_());
        p_225623_4_.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
        this.itemRenderer.func_229110_a_(((IRendersAsItem) p_225623_1_).getItem(), ItemCameraTransforms.TransformType.GROUND, p_225623_6_, OverlayTexture.field_229196_a_, p_225623_4_, p_225623_5_);
        p_225623_4_.func_227865_b_();
        super.func_225623_a_(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
    }

    public ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
    }
}
