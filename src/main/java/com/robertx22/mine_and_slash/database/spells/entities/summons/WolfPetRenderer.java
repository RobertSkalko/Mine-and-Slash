package com.robertx22.mine_and_slash.database.spells.entities.summons;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class WolfPetRenderer extends MobRenderer<BaseSummonedEntity, WolfPetModel<BaseSummonedEntity>> {
    private static final ResourceLocation WOLF_TEXTURES = new ResourceLocation(Ref.MODID, "textures/entity/wolf.png");

    public WolfPetRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WolfPetModel<>(), 0.5F);
    }

    @Override
    protected float handleRotationFloat(BaseSummonedEntity livingBase, float partialTicks) {
        return 1.5393804F;
    }

    @Override
    public void render(BaseSummonedEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

    }

    @Override
    public ResourceLocation getEntityTexture(BaseSummonedEntity entity) {
        return WOLF_TEXTURES;
    }

}