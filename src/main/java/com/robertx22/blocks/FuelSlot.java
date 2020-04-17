package com.robertx22.blocks;

import java.util.HashMap;

import com.robertx22.items.ores.ItemOre;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FuelSlot extends Slot {

	public FuelSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	 @Override
	    public boolean isItemValid(ItemStack stack) {
	        return FUEL_VALUES.get(stack.getItem()) != null;
	    }

	    public static HashMap<Item, Integer> FUEL_VALUES = new HashMap<Item, Integer>() {
	        {
	            {

	                put(Items.DIAMOND, 300);
	                put(Items.GOLD_INGOT, 80);
	                put(Items.IRON_INGOT, 20);
	                put(Items.EMERALD, 200);
	                put(Items.REDSTONE, 3);

	                int ore = 3;

	                for (int i = 0; i < 6; i++) {
	                    put(ItemOre.ItemOres.get(i), ore);
	                    ore *= 9;

	                }

	            }
	        }
	    };
}