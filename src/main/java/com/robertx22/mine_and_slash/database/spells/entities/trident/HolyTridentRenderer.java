package com.robertx22.mine_and_slash.database.spells.entities.trident;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TridentRenderer;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.ResourceLocation;

public class HolyTridentRenderer extends TridentRenderer {

    public HolyTridentRenderer(EntityRendererManager p_i48828_1_) {
        super(p_i48828_1_);
    }

    public static final ResourceLocation TRIDENT = new ResourceLocation(Ref.MODID, "textures/entity/holy_trident.png");

    @Override
    public ResourceLocation getEntityTexture(TridentEntity en) {
        return TRIDENT;
    }
}
