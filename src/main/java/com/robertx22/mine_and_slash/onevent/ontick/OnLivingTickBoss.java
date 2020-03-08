package com.robertx22.mine_and_slash.onevent.ontick;

import com.robertx22.mine_and_slash.uncommon.capability.entity.BossCap;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class OnLivingTickBoss {

    @SubscribeEvent
    public static void onTick(LivingEvent.LivingUpdateEvent event) {

        try {
            if (event.getEntityLiving() == null) {
                return;
            }

            LivingEntity x = event.getEntityLiving();

            if (x.ticksExisted % 4 == 0) {

                x.getCapability(BossCap.Data)
                    .ifPresent(c -> {
                        c.spawnParticle((LivingEntity) x);
                    });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
