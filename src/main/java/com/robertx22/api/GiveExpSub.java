package com.robertx22.api;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;

@Mod.EventBusSubscriber
public class GiveExpSub {

  @net.minecraftforge.fml.common.eventhandler.SubscribeEvent
  public static void onGiveExp(MineAndSlashEvents.GiveExpEvent event) {

    if (event.getResult().equals(Event.Result.ALLOW)) {

      if (event.isCanceled() == false) {


        if (event.experience > 0) {

          EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
          event.playerCapability.GiveExp(player, event.experience);

        }
      }

    }
  }
}
