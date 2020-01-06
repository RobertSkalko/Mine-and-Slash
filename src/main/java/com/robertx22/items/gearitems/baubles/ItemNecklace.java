package com.robertx22.items.gearitems.baubles;

import java.util.HashMap;

import com.robertx22.items.gearitems.bases.BaseBaublesItem;

import baubles.api.BaubleType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemNecklace extends BaseBaublesItem {

	public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

	public ItemNecklace(BaubleType type) {
		super(type);
	}
}