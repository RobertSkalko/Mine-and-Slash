package com.robertx22.onevent.player;

import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnPlayerClone {

	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone event) {

		if (!event.getEntityPlayer().world.isRemote) {

			EntityPlayer player = event.getEntityPlayer();
			EntityData.UnitData data = (UnitData) player.getCapability(EntityData.Data, null);
			EntityData.UnitData oldData = (UnitData) event.getOriginal().getCapability(EntityData.Data, null);
			data.setNBT(oldData.getNBT());

		}

	}

}
