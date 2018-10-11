package com.robertx22.onevent.gearupgrade;

import com.robertx22.customitems.MyItems;
import com.robertx22.utilityclasses.ItemUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnAnvilRepair {

	@SubscribeEvent
	public void OnAnvilDoRepair(AnvilUpdateEvent event) {

		Item mat = event.getRight().getItem();
		ItemStack item = event.getLeft().copy();

		int repaired = 0;

		if (mat.equals(MyItems.magic_ore)) {
			repaired = 50;
		} else if (mat.equals(MyItems.rare_ore)) {
			repaired = 150;
		} else if (mat.equals(MyItems.epic_ore)) {
			repaired = 250;
		} else if (mat.equals(MyItems.legendary_ore)) {
			repaired = 400;
		} else if (mat.equals(MyItems.mythical_ore)) {
			repaired = 500;
		}

		item.setItemDamage(item.getItemDamage() - repaired);

		if (ItemUtils.isGear(event.getLeft())) {

			event.setOutput(item);

			event.setCost(1);
			event.setMaterialCost(1);

		}

	}
}
