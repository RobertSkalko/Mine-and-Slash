package com.robertx22.mine_and_slash.items.spells;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.SimpleBakedModel;

public class InGame3DWhile2DInvModel extends SimpleBakedModel {

    public InGame3DWhile2DInvModel(SimpleBakedModel model) {
        super(model.generalQuads, model.faceQuads, model.isAmbientOcclusion(), model.isGui3d(), model
                .getParticleTexture(), model.getItemCameraTransforms(), model.getOverrides());

        this.renderModel = new InGame3DWhile2DInvModel(this, true);

    }

    public InGame3DWhile2DInvModel(InGame3DWhile2DInvModel model, boolean hasRenderer) {
        super(model.generalQuads, model.faceQuads, model.isAmbientOcclusion(), model.isGui3d(), model
                .getParticleTexture(), model.getItemCameraTransforms(), model.getOverrides());
        this.hasRenderer = hasRenderer;
    }

    InGame3DWhile2DInvModel renderModel;

    boolean hasRenderer = false;

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return hasRenderer;
    }

    @Override
    public org.apache.commons.lang3.tuple.Pair<? extends IBakedModel, javax.vecmath.Matrix4f> handlePerspective(
            ItemCameraTransforms.TransformType cameraTransformType) {

        if (cameraTransformType == ItemCameraTransforms.TransformType.GUI || cameraTransformType == ItemCameraTransforms.TransformType.GROUND) {
            return net.minecraftforge.client.ForgeHooksClient.handlePerspective(getBakedModel(), cameraTransformType);
        } else {
            return net.minecraftforge.client.ForgeHooksClient.handlePerspective(renderModel
                    .getBakedModel(), cameraTransformType);
        }
    }

}
