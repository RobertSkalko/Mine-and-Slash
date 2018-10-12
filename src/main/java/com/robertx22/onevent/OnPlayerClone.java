package com.robertx22.onevent;

import com.robertx22.capability.EntityData;
import com.robertx22.capability.EntityData.IData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saving.Saving;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnPlayerClone {

	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {

		if (!event.getEntityPlayer().world.isRemote) {

			// event.getEntityPlayer().sendMessage(new TextComponentString("clone"));

			EntityPlayer player = event.getEntityPlayer();

			IData data = player.getCapability(EntityData.Data, null);

			IData oldData = event.getOriginal().getCapability(EntityData.Data, null);

			data.setNBT(oldData.getNBT());

			Unit unit = Saving.Load(data.getNBT(), Unit.class);

			unit.updateClientExpGUI(player);

		}

	}

}
