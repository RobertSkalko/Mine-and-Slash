package com.robertx22.mine_and_slash.database.spells.entities.magic_missiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class MagicMissileModel extends Model {

    private final ModelRenderer modelRenderer = new ModelRenderer(16, 16, 0, 0);

    public MagicMissileModel() {
        super(RenderType::getEntitySolid);

        modelRenderer.setRotationPoint(0.0F, 24.0F, 0.0F);
        modelRenderer.addBox(0, 0, -3.0F, -4.0F, 0.0F, 3, 3, 5, 0.0F);
        modelRenderer.addBox(0, 0, -2.0F, -5.0F, 1.0F, 1, 1, 3, 0.0F);
        modelRenderer.addBox(0, 0, -2.0F, -1.0F, 1.0F, 1, 1, 3, 0.0F);
        modelRenderer.addBox(0, 0, 0.0F, -3.0F, 1.0F, 1, 1, 3, 0.0F);
        modelRenderer.addBox(0, 0, -4.0F, -3.0F, 1.0F, 1, 1, 3, 0.0F);

    }

    public void render(MatrixStack matrix, IVertexBuilder vertex, int p_225598_3_, int p_225598_4_, float p_225598_5_,
                       float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        this.modelRenderer.render(
            matrix, vertex, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
    }
}
