package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnDeath {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerDeathSetTeleportBack(LivingDeathEvent evt) {

        try {

            if (true) {
                return;
            }

            LivingEntity living = evt.getEntityLiving();

            if (living.world.isRemote) {
                return;
            }

            if (living instanceof PlayerEntity) {

                PlayerEntity player = (PlayerEntity) living;

                Load.Unit(player)
                    .onDeath(player);

                if (WorldUtils.isMapWorldClass(living.world)) {

                    PlayerMapCap.IPlayerMapData data = Load.playerMapData(player);

                    data.onPlayerDeath(player);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
