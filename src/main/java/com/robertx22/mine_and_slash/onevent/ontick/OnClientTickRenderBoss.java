package com.robertx22.mine_and_slash.onevent.ontick;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class OnClientTickRenderBoss {

    private static long lastRender = 0L;

    @SubscribeEvent
    public static void onRenderWorldLast(RenderWorldLastEvent event) {

        if (true) {
            return; // TODO
        }

        if (Minecraft.getInstance().world == null) {
            return;
        }

        if (Minecraft.getInstance().player.ticksExisted % 2 == 0) {

            Minecraft.getInstance().world.getAllEntities().forEach(x -> {
                if (x instanceof SheepEntity) {
                    particle((LivingEntity) x);
                }
            });
        }
    }

    private static void particle(LivingEntity ent) {
        Minecraft.getInstance().worldRenderer.addParticle(ParticleTypes.WITCH, true,
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

}
