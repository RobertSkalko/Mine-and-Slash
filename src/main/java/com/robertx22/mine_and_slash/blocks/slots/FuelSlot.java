package com.robertx22.mine_and_slash.blocks.slots;

import com.robertx22.mine_and_slash.items.ores.ItemOre;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.HashMap;

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
                put(Items.GOLD_INGOT, 100);
                put(Items.IRON_INGOT, 20);
                put(Items.EMERALD, 200);
                put(Items.REDSTONE, 3);

                put(ItemOre.ItemOres.get(0), 5);
                put(ItemOre.ItemOres.get(1), 25);
                put(ItemOre.ItemOres.get(2), 125);
                put(ItemOre.ItemOres.get(3), 500);
                put(ItemOre.ItemOres.get(4), 2000);
                put(ItemOre.ItemOres.get(5), 10000);

            }
        }
    };

}