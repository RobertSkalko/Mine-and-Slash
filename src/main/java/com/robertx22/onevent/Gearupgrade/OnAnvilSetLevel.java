package com.robertx22.onevent.Gearupgrade;

import com.robertx22.capability.EntityData;
import com.robertx22.player.PlayerData;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnAnvilSetLevel {

	@SubscribeEvent
	public void setLevelBack(AnvilRepairEvent event) {

		if (!event.getEntityLiving().world.isRemote) {

			EntityPlayer player = event.getEntityPlayer();

			EntityData.IData data = player.getCapability(EntityData.Data, null);

			PlayerData.updateClientXPAndLvl(player);

			event.setBreakChance(0);

		}

	}

}