package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnDeath {

    // this is needed cus otherwise it crashes with removing ticking entity
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onTickTeleportPlayerBackIfDead(TickEvent.PlayerTickEvent evt) {

        try {

            if (true) {
                return;
            }

            if (evt.player != null && evt.player.world != null) {
                if (evt.player.world.isRemote == false) {
                    PlayerMapCap.IPlayerMapData data = Load.playerMapData(evt.player);
                    if (data != null) {

                        if (ModConfig.INSTANCE.Server.DISABLE_DEATH_IN_MAPS.get()) {
                            data.onTickIfDead((ServerPlayerEntity) evt.player);
                        }

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

                    if (ModConfig.INSTANCE.Server.DISABLE_DEATH_IN_MAPS.get()) {
                        player.setLocationAndAngles(player.posX, player.posY + 10, player.posZ, 0, 0);
                        evt.setCanceled(true);
                    }

                    data.onPlayerDeath(player);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
