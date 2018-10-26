package com.robertx22.onevent.Item;

import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnPickup {

	@SubscribeEvent
	public static void pickupItem(EntityItemPickupEvent event) {

		/*
		 * ItemStack item = event.getItem().getItem();
		 * 
		 * if (!(event.getEntityLiving() instanceof EntityPlayer)) { return; } if
		 * (!item.hasTagCompound()) { return; }
		 * 
		 */
		/*
		 * if (ItemUtils.isGear(item) &&
		 * item.getTagCompound().getInteger(Tags.RARITY_NUMBER) <
		 * ModConfig.Options.PICKUP_ONLY_RARITIES_GEAR) { event.setCanceled(true); }
		 * 
		 * if (ItemUtils.isSocket(item.getItem()) && item.getTagCompound()
		 * .getInteger(Tags.RARITY_NUMBER) <
		 * ModConfig.Options.PICKUP_ONLY_RARITIES_SOCKETS) { event.setCanceled(true); }
		 */

	}

}
