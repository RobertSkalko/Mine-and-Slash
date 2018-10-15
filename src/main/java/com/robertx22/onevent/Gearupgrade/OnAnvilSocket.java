package com.robertx22.onevent.gearupgrade;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnAnvilSocket {

	@SubscribeEvent
	public static void OnAnvilDoSocket(AnvilUpdateEvent event) {

		Item mat = event.getRight().getItem();
		ItemStack item = event.getLeft().copy();
		/*
		 * if (ItemUtils.isGear(event.getLeft())) {
		 * 
		 * if (ItemUtils.isSocket(mat)) {
		 * 
		 * if (item.getTagCompound().getString(Tags.GEAR_TYPE)
		 * .equals(event.getRight().getTagCompound().getString(Tags.GEAR_TYPE))) {
		 * 
		 * event.setCost(1); event.setMaterialCost(1);
		 * 
		 * item = transferSocketToItem(item, event.getRight());
		 * 
		 * event.setOutput(item); } else { event.setCost(0); }
		 * 
		 * }
		 * 
		 * }
		 */

	}

}
