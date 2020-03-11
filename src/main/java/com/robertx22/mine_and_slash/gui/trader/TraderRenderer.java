package com.robertx22.mine_and_slash.gui.trader;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.util.ResourceLocation;

public class TraderRenderer extends MobRenderer<TraderEntity, VillagerModel<TraderEntity>> {
    private static final ResourceLocation LOC = new ResourceLocation("textures/entity/villager/villager.png");

    public TraderRenderer(EntityRendererManager man) {
        super(man, new VillagerModel(0.0F), 0.5F);
        this.addLayer(new HeadLayer(this));
        this.addLayer(new CrossedArmsItemLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(TraderEntity p_110775_1_) {
        return LOC;
    }

    @Override
    protected void preRenderCallback(TraderEntity en, MatrixStack matrix, float aaa) {
        float f = 0.9375F;

        if (en.isChild()) {
            f = (float) ((double) f * 0.5D);
            this.shadowSize = 0.25F;
        } else {
            this.shadowSize = 0.5F;
        }

        matrix.scale(f, f, f);
    }
}
