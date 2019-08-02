package com.robertx22.mine_and_slash.database.spells.entities.bases.renderers;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class spellRenderer extends EntityRenderer {

    public static ResourceLocation FIRE_TEX = new ResourceLocation("textures/entity/slime/magmacube.png");

    ResourceLocation loc;
    Model model;

    public spellRenderer(EntityRendererManager p_i46179_1_) {
        super(p_i46179_1_);

    }

    public spellRenderer build(ResourceLocation loc, Model model) {
        this.loc = loc;
        this.model = model;
        return this;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }
}
