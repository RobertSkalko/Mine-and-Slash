package com.robertx22.mine_and_slash.onevent.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TridentSpawn {

    @SubscribeEvent
    public static void onTridentCast(EntityJoinWorldEvent event) {

        if (event.getWorld().isRemote) {
            return;
        }

        if (event.getEntity() instanceof TridentEntity) {

            TridentEntity trident = (TridentEntity) event.getEntity();

            if (trident.thrownStack != null && !trident.thrownStack.isEmpty()) {
                if (trident.getShooter() instanceof PlayerEntity) {
                    // Load.lastThrown((PlayerEntity) trident.getShooter())
                    //   .set(trident.thrownStack, (PlayerEntity) trident.getShooter());
                }
            }

        }

    }
}
