package com.robertx22.onevent;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.IEntityData;
import com.robertx22.uncommon.datasaving.UnitSaving;

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
			EntityData.IEntityData data = (IEntityData) player.getCapability(EntityData.Data, null);
			EntityData.IEntityData oldData = (IEntityData) event.getOriginal().getCapability(EntityData.Data, null);
			data.setNBT(oldData.getNBT());
			Unit unit = UnitSaving.Load(player);
			unit.updateClientExpGUI(player);

		}

	}

}
