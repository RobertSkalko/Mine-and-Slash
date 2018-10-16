package com.robertx22.crafting;

import com.robertx22.datasaving.Saving;
import com.robertx22.saveclasses.GearItemData;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeUtils {

	public static ItemStack FirstItemIsGear(InventoryCrafting inv) {

		ItemStack stack = inv.getStackInSlot(0);
		GearItemData data = Saving.Load(stack);
		if (data != null) {
			return stack;
		}

		return null;
	}

	public static ItemStack SecondItemIs(InventoryCrafting inv, Item item) {

		// count == 1 is a a baindaid fix for the duplication glitch of chaos orbs
		ItemStack stack = inv.getStackInSlot(1);
		if (stack.getItem().getRegistryName().equals(item.getRegistryName()) && stack.getCount() == 1) {
			return stack;
		}

		return null;
	}

	public static ItemStack AnyItemIsGearItem(InventoryCrafting inv) {

		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);

			GearItemData data = Saving.Load(stack);
			if (data != null) {
				return stack;
			}

		}

		return null;
	}

	public static ItemStack AnyItemIs(InventoryCrafting inv, Item item) {

		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);

			if (stack.getItem() == item) {
				return stack;
			}
		}

		return null;
	}

}
