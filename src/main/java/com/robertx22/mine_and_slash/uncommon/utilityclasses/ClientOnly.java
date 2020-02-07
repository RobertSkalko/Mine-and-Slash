package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public class ClientOnly {

    @Nullable
    public static Entity getEntityByUUID(World world, @Nullable UUID id) {

        if (world instanceof ClientWorld) {
            for (Entity entity : ((ClientWorld) world).getAllEntities()) {
                if (entity.getUniqueID().equals(id)) {

                    return entity;
                }
            }
        }
        return null;

    }

    public static void bossParticle(LivingEntity ent, IParticleData p) {
        Minecraft.getInstance().worldRenderer.addParticle(p, true,
                                                          ent.posX + (ent.world.rand.nextDouble() - 0.5D) * (double) ent
                                                                  .getWidth(),
                                                          ent.posY + ent.world.rand.nextDouble() * (double) ent.getHeight() - 0.25D,
                                                          ent.posZ + (ent.world.rand.nextDouble() - 0.5D) * (double) ent
                                                                  .getWidth(),
                                                          (ent.world.rand.nextDouble() - 0.5D) * 2.0D,
                                                          -ent.world.rand.nextDouble(),
                                                          (ent.world.rand.nextDouble() - 0.5D) * 2.0D
        );
    }

    public static PlayerEntity getPlayer() {
        return Minecraft.getInstance().player;
    }
}
