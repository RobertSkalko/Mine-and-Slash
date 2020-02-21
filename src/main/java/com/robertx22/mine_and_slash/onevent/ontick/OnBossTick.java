package com.robertx22.mine_and_slash.onevent.ontick;

import com.robertx22.mine_and_slash.uncommon.capability.entity.BossCap;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

import java.util.ArrayList;
import java.util.List;

public class OnBossTick {

    static int micros = 0;

    public static List<LivingEntity> bossList = new ArrayList<>();

    static int ticksToClear = 5000;
    static int clearTicks = 0;

    // bosses need to be added on join world event
    @SubscribeEvent
    public static void onTick(TickEvent.ServerTickEvent event) {
        if (event.side.equals(LogicalSide.SERVER) && event.phase == TickEvent.Phase.END) {

            clearTicks++;

            if (clearTicks > ticksToClear) {
                clearTicks = 0;
                bossList.removeIf(x -> x == null || !x.isAlive());
            }

            for (LivingEntity e : bossList) {

                if (e != null) {
                    e.getCapability(BossCap.Data).ifPresent(x -> x.onTick(e));
                }

            }

        }
    }
}
