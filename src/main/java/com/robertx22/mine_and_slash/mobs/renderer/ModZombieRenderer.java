package com.robertx22.mine_and_slash.mobs.renderer;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;

public class ModZombieRenderer extends AbstractZombieRenderer<ZombieEntity, ZombieModel<ZombieEntity>> {
    public ModZombieRenderer(EntityRendererManager manager) {
        super(manager, new ZombieModel(0.0F, false), new ZombieModel(0.5F, true), new ZombieModel(1.0F, true));
    }

    private static final ResourceLocation TEX = new ResourceLocation(Ref.MODID, "textures/entity/zombie/leaping_zombie.png");

    @Override
    public ResourceLocation getEntityTexture(ZombieEntity p_110775_1_) {
        return TEX;
    }

}
