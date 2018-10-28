package com.robertx22.crafting;

import java.util.List;

import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.GearSaving;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeUtils {

	public static ItemStack FirstItemIsGear(InventoryCrafting inv) {

		ItemStack stack = inv.getStackInSlot(0);
		GearItemData data = GearSaving.Load(stack);
		if (data != null) {
			return stack;
		}

		return null;
	}

	public static boolean AreAllOtherSlotsEmpty(InventoryCrafting inv, List<Integer> slots) {

		for (int i = 0; i < inv.getSizeInventory(); i++) {

			if (slots.contains(i))
				continue;

			if (inv.getStackInSlot(i) != ItemStack.EMPTY) {
				return false;
			}

		}
		return true;

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

			GearItemData data = GearSaving.Load(stack);
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
