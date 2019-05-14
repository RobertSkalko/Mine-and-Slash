package com.robertx22.items.gearitems.armor;

import java.util.HashMap;

import com.robertx22.items.gearitems.bases.BaseArmorItem;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

public class ItemHelmet extends BaseArmorItem {
	public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

	public ItemHelmet(int rarity) {
		super(rarity, EntityEquipmentSlot.HEAD);

	}

	@Override
	public String Name() {
		return "Helmet";
	}

}
