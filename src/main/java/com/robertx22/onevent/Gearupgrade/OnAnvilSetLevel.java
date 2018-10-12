package com.robertx22.onevent.gearupgrade;

import com.robertx22.capability.EntityData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saving.Saving;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnAnvilSetLevel {

	@SubscribeEvent
	public void setLevelBack(AnvilRepairEvent event) {

		if (!event.getEntityLiving().world.isRemote) {

			EntityPlayer player = event.getEntityPlayer();

			EntityData.IData data = player.getCapability(EntityData.Data, null);

			Unit unit = Saving.Load(player, Unit.class);

			unit.updateClientExpGUI(player);

			event.setBreakChance(0);

		}

	}

}