package com.robertx22.onevent.ontick;

import java.util.HashMap;
import java.util.UUID;

import com.robertx22.customitems.misc.ItemMapBackPortal;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

// makes sure players always have a portal to get back from a map
@Mod.EventBusSubscriber
public class OnTickCheckIfMapPortal {

	public static HashMap<UUID, Integer> ticks = new HashMap<UUID, Integer>();

	@SubscribeEvent
	public static void onTickCheckPortalItem(TickEvent.PlayerTickEvent event) {

		if (event.side.isClient())
			return;

		UUID id = event.player.getUniqueID();

		if (ticks.containsKey(id)) {
			ticks.put(id, ticks.get(id) + 1);
		} else {
			ticks.put(id, 1);
		}

		if (ticks.get(id) > 3000) {

			ticks.put(id, 0);

			IWorldData data = Load.World(event.player.world);

			if (data.isMapWorld()) {
				EntityPlayer player = event.player;

				ItemStack portalitem = new ItemStack(ItemMapBackPortal.ITEM);

				if (!player.inventory.hasItemStack(portalitem)) {
					player.inventory.addItemStackToInventory(portalitem);

				}
			}
		}
	}
}
