package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.network.EfficientMobUnitPacket;
import com.robertx22.mine_and_slash.network.EntityUnitPacket;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnTrackEntity {

    @SubscribeEvent
    public static void onEntityTrack(PlayerEvent.StartTracking event) {

        Entity entity = event.getTarget();

        if (entity instanceof LivingEntity) {

            if (entity.isEntityEqual(event.getEntityPlayer()) == false) {

                if (Load.hasUnit(entity)) {

                    if (entity instanceof PlayerEntity) {
                        MMORPG.sendToClient(new EfficientMobUnitPacket(entity, Load.Unit(entity)), (ServerPlayerEntity) event
                                .getEntityPlayer());
                    } else {
                        MMORPG.sendToClient(new EntityUnitPacket(entity, Load.Unit(entity)), (ServerPlayerEntity) event
                                .getEntityPlayer());
                    }

                }

            }
        }

    }
}
