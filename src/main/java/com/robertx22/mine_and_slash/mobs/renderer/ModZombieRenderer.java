package com.robertx22.mine_and_slash.mobs.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;

public class ModZombieRenderer extends AbstractZombieRenderer<ZombieEntity, ZombieModel<ZombieEntity>> {

    float scale = 1;

    public ModZombieRenderer scaleBy(float scale) {
        this.scale = scale;
        return this;
    }

    public ModZombieRenderer(EntityRendererManager manager, String textureName) {
        super(manager, new ZombieModel(0.0F, false), new ZombieModel(0.5F, true), new ZombieModel(1.0F, true));

        TEX = new ResourceLocation(Ref.MODID, "textures/entity/zombie/" + textureName + ".png");

    }

    private final ResourceLocation TEX;

    @Override
    protected void preRenderCallback(ZombieEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getEntityTexture(ZombieEntity p_110775_1_) {
        return TEX;
    }

}
