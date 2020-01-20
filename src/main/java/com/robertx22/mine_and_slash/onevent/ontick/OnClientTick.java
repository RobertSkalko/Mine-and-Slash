package com.robertx22.mine_and_slash.onevent.ontick;

import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class OnClientTick {

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {

        if (event.side.equals(LogicalSide.CLIENT) && event.phase == TickEvent.Phase.END) {

            PlayerEntity player = event.player;

            Load.spells(player).getSpellData().onTimePass(1); // ticks spells so i dont need to sync packets every tick
        }
    }
}
