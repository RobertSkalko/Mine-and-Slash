package com.robertx22.mine_and_slash.database.spells.entities.bases.renderers;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class firebolt extends EntityModel {
    private final RendererModel bone;

    public firebolt() {
        textureWidth = 16;
        textureHeight = 16;

        bone = new RendererModel(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(bone, -1.5708F, 0.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 0, 0, -2.961F, -1.0119F, -24.299F, 4, 3, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -2.3857F, -1.462F, -24.299F, 3, 4, 3, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -2.3857F, -1.0119F, -26.884F, 3, 3, 7, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -1.847F, -0.6083F, -20.304F, 2, 2, 0, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -1.7893F, -0.6083F, -28.9379F, 2, 2, 2, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -1.3121F, -0.0855F, -30.926F, 1, 1, 2, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4,
                       float f5) {
        bone.render(f5);
    }

    public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}