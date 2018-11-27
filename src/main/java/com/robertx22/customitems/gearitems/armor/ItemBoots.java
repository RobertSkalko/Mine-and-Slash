package com.robertx22.customitems.gearitems.armor;

import java.util.HashMap;

import com.robertx22.customitems.gearitems.bases.BaseArmorItem;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

public class ItemBoots extends BaseArmorItem {
	public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

	public ItemBoots(int rarity) {
		super(rarity, EntityEquipmentSlot.FEET);

	}

	@Override
	public String Name() {
		return "Boots";
	}

}
