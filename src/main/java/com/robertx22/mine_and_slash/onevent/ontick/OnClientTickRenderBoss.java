package com.robertx22.mine_and_slash.onevent.ontick;

import com.robertx22.mine_and_slash.uncommon.capability.BossCap;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class OnClientTickRenderBoss {

    private static long lastRender = 0L;

    @SubscribeEvent
    public static void onRenderWorldLast(RenderWorldLastEvent event) {

        if (Minecraft.getInstance().world == null) {
            return;
        }

        if (Minecraft.getInstance().player.ticksExisted % 3 == 0) {

            Minecraft.getInstance().world.getAllEntities().forEach(x -> {
                x.getCapability(BossCap.Data).ifPresent(c -> {
                    particle((LivingEntity) x, c.getBoss().getParticle());
                });
            });
        }
    }

    private static void particle(LivingEntity ent, IParticleData p) {
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

}
