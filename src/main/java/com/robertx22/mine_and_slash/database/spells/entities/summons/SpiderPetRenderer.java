package com.robertx22.mine_and_slash.database.spells.entities.summons;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.util.ResourceLocation;

public class SpiderPetRenderer<T extends SpiderPetEntity> extends MobRenderer<T, SpiderModel<T>> {
    private static final ResourceLocation SPIDER_TEXTURES = new ResourceLocation("textures/entity/spider/spider.png");

    public SpiderPetRenderer(EntityRendererManager manager) {
        super(manager, new SpiderModel(), 0.8F);
        this.addLayer(new SpiderEyesLayer(this));
    }

    protected float getDeathMaxRotation(T p_77037_1_) {
        return 180.0F;
    }

    public ResourceLocation getEntityTexture(T en) {
        return SPIDER_TEXTURES;
    }
}
