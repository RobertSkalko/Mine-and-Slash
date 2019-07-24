package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.api.MineAndSlashEvents;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GiveExpSub {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onGiveExp(MineAndSlashEvents.GiveExpEvent event) {

        if (event.getResult().equals(Event.Result.ALLOW)) {

            if (event.isCanceled() == false) {

                if (event.experience > 0) {

                    ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
                    if (event.playerCapability == null) {
                        Load.Unit(event.getEntityLiving())
                                .GiveExp(player, event.experience);
                    } else {
                        event.playerCapability.GiveExp(player, event.experience);
                    }

                }
            }

        }
    }
}

