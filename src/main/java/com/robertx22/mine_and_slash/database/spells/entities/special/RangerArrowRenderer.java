package com.robertx22.mine_and_slash.database.spells.entities.special;

import com.robertx22.mine_and_slash.database.spells.entities.proj.RangerArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class RangerArrowRenderer extends ArrowRenderer<RangerArrowEntity> {
    public static final ResourceLocation RES_ARROW = new ResourceLocation("textures/entity/projectiles/arrow.png");
    //public static final ResourceLocation RES_TIPPED_ARROW = new ResourceLocation("textures/entity/projectiles/tipped_arrow.png");

    public RangerArrowRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(RangerArrowEntity en) {
        return RES_ARROW;
    }

}
